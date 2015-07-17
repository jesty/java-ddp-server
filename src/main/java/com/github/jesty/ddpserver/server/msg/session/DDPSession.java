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
	private String sessionId;

	public DDPSession(Session session){
		this(session, new ObjectMapper());
	}
	
	public DDPSession(Session session, ObjectMapper mapper){
		this.session = session;
		this.mapper = mapper;
		this.sessionId = session.getId();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DDPSession other = (DDPSession) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
	
	

}
