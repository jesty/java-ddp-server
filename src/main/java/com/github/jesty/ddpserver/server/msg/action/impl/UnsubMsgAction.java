package com.github.jesty.ddpserver.server.msg.action.impl;

import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class UnsubMsgAction implements MsgAction<Unsub> {

	@Override
	public void execute(Unsub object, DDPSession session) {
		System.out.println("called: " + this.getClass());
	}

}
