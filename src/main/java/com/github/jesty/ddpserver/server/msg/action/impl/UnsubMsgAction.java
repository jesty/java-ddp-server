package com.github.jesty.ddpserver.server.msg.action.impl;

import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.pubsub.PubSub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class UnsubMsgAction implements MsgAction<Unsub> {

	private PubSub pubSub;

	public UnsubMsgAction(PubSub pubSub){
		this.pubSub = pubSub;
	}

	@Override
	public void execute(Unsub unsub, DDPSession session) {
		pubSub.unsub(unsub, session);
	}

}
