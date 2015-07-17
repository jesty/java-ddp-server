package com.github.jesty.ddpserver.server.msg.action.impl;

import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.pubsub.PubSub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class SubMsgAction implements MsgAction<Sub> {
	
	private PubSub pubSub;
	
	public SubMsgAction(PubSub pubSub){
		this.pubSub = pubSub;
	}

	@Override
	public void execute(Sub sub, DDPSession session) {
		pubSub.sub(sub, session);
	}

}
