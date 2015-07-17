package com.github.jesty.ddpserver.server;

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.server.msg.InboundMessagesParser;
import com.github.jesty.ddpserver.server.msg.InboundMessagesParserObjectMapperFactoryImpl;
import com.github.jesty.ddpserver.server.msg.JacksonInboundMessagesParser;
import com.github.jesty.ddpserver.server.msg.action.InboundMsgActionSelector;
import com.github.jesty.ddpserver.server.msg.action.MapInboundMsgActionSelector;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.method.DefaultIdGenerator;
import com.github.jesty.ddpserver.server.msg.method.IdGenerator;
import com.github.jesty.ddpserver.server.msg.pubsub.MapPubSub;
import com.github.jesty.ddpserver.server.msg.pubsub.PubSub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;
import com.mongodb.DB;
import com.mongodb.MongoClient;
 
@ServerEndpoint(value = "/websocket")
public class DDPWebSocket {
 
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private InboundMessagesParser<HasMsg> inboundMessagesParser;
    private InboundMsgActionSelector inboundMsgActionSelector;
    
    public DDPWebSocket(InboundMessagesParser<HasMsg> inboundMessagesParser) {
		this.inboundMessagesParser = inboundMessagesParser;
	}
    
    public DDPWebSocket(){
    	this.inboundMessagesParser = new JacksonInboundMessagesParser(new InboundMessagesParserObjectMapperFactoryImpl());
    	PubSub pubSub = new MapPubSub();
		IdGenerator idGenerator = new DefaultIdGenerator();
		this.inboundMsgActionSelector = new MapInboundMsgActionSelector(pubSub, idGenerator, initDB());
    	this.inboundMessagesParser.init();
    }
 
	@OnOpen
    public void onOpen(Session session) {
    	logger.info("Connected ... " + session.getId());
    }
 
    @OnMessage
    public void onMessage(String message, Session session) {
    	if(logger.isLoggable(Level.FINE)){
    		logger.fine(session.getId() + ": " + message);
    	}
    	HasMsg obj = this.inboundMessagesParser.parse(message);
    	MsgAction<HasMsg> action = (MsgAction<HasMsg>) this.inboundMsgActionSelector.select((Class<? extends HasMsg>) obj.getClass());
		action.execute(obj, new DDPSession(session));
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
    
    private DB initDB() {
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		DB db = mongoClient.getDB("test");
		return db;
	}
    
}
