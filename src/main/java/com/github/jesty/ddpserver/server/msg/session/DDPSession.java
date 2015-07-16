package com.github.jesty.ddpserver.server.msg.session;

import java.io.IOException;

import javax.websocket.Session;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.server.msg.action.GenericException;
import com.github.jesty.ddpserver.server.msg.action.JsonException;

public class DDPSession {

	private Session session;
	private ObjectMapper mapper;

	public DDPSession(Session session){
		this.session = session;
		mapper = new ObjectMapper();
	}
	
	public DDPSession(Session session, ObjectMapper mapper){
		this.session = session;
		this.mapper = mapper;
	}

	public Session getSession() {
		return session;
	}

	public void sendMsg(HasMsg object){
		if(object != null){
			String msg;
			try {
				msg = mapper.writeValueAsString(object);
				session.getBasicRemote().sendText(msg);
			} catch (JsonGenerationException e) {
				throw new JsonException(e);
			} catch (JsonMappingException e) {
				throw new JsonException(e);
			} catch (IOException e) {
				throw new GenericException(e);
			}
		}
	}

}
