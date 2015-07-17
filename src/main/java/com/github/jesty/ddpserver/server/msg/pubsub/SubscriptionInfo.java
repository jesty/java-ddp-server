package com.github.jesty.ddpserver.server.msg.pubsub;

import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class SubscriptionInfo {

	private final DDPSession session;
	private final Sub sub;

	public SubscriptionInfo(Sub sub, DDPSession session) {
		this.sub = sub;
		this.session = session;
	}
	
	public Sub getSub() {
		return sub;
	}
	
	public DDPSession getSession() {
		return session;
	}

}
