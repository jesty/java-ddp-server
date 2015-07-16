package com.github.jesty.ddpserver.server.msg.action;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public interface MsgAction<T extends HasMsg> {
	
	void execute(T obj, DDPSession session);

}
