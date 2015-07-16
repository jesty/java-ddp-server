package com.github.jesty.ddpserver.server.msg.action.impl;

import com.github.jesty.ddpserver.model.hearthbit.Ping;
import com.github.jesty.ddpserver.model.hearthbit.Pong;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class PingMsgAction implements MsgAction<Ping> {

	@Override
	public void execute(final Ping ping, DDPSession session) {
		Pong pong = new Pong(){{
			setId(ping.getId());
		}};
		session.sendMsg(pong);
	}

}
