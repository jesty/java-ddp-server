package com.github.jesty.ddpserver.server.msg.action.impl;

import java.util.UUID;

import com.github.jesty.ddpserver.model.connection.Connect;
import com.github.jesty.ddpserver.model.connection.Connected;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class ConnectMsgAction implements MsgAction<Connect> {

	@Override
	public void execute(Connect object, DDPSession session) {
		Connected connected = new Connected();
		connected.setSession(UUID.randomUUID().toString());
		session.sendMsg(connected);
	}

}
