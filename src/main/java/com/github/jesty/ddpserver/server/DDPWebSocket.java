package com.github.jesty.ddpserver.server;

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
import com.github.jesty.ddpserver.server.msg.session.DDPSession;
 
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
    	this.inboundMsgActionSelector = new MapInboundMsgActionSelector();
    	this.inboundMessagesParser.init();
    }
 
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected ... " + session.getId());
    }
 
    @OnMessage
    public void onMessage(String message, Session session) {
    	HasMsg obj = this.inboundMessagesParser.parse(message);
    	MsgAction<HasMsg> action = (MsgAction<HasMsg>) this.inboundMsgActionSelector.select((Class<? extends HasMsg>) obj.getClass());
		action.execute(obj, new DDPSession(session));
    }
 
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
    	System.out.println(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
}
