package com.github.jesty.ddpserver.server.msg.pubsub;

import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public interface PubSub {

	void sub(Sub sub, DDPSession session);

	void unsub(Unsub unsub, DDPSession session);
	
	void fireEvent(Event event);

}
