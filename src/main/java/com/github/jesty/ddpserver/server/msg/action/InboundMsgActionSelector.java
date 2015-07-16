package com.github.jesty.ddpserver.server.msg.action;

import com.github.jesty.ddpserver.model.HasMsg;

public interface InboundMsgActionSelector {
	
	MsgAction<? extends HasMsg> select(Class<? extends HasMsg> msgClass);

}
