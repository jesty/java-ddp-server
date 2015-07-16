package com.github.jesty.ddpserver.server.msg.session;

import javax.websocket.Session;

public interface SessionRepository {
	
	void add(Session session);
	
	boolean remove(String id);
	
	DDPSession get(String id);
	
}
