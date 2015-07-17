package com.github.jesty.ddpserver.server.msg.action;

import java.util.HashMap;
import java.util.Map;

import com.github.jesty.ddpserver.model.connection.Connect;
import com.github.jesty.ddpserver.model.hearthbit.Ping;
import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.model.rpc.Method;
import com.github.jesty.ddpserver.server.msg.action.impl.ConnectMsgAction;
import com.github.jesty.ddpserver.server.msg.action.impl.MethodMsgAction;
import com.github.jesty.ddpserver.server.msg.action.impl.PingMsgAction;
import com.github.jesty.ddpserver.server.msg.action.impl.SubMsgAction;
import com.github.jesty.ddpserver.server.msg.action.impl.UnsubMsgAction;
import com.github.jesty.ddpserver.server.msg.method.IdGenerator;
import com.github.jesty.ddpserver.server.msg.method.MongoDBMethodInvoker;
import com.github.jesty.ddpserver.server.msg.method.ReflectionMethodInvoker;
import com.github.jesty.ddpserver.server.msg.pubsub.PubSub;
import com.mongodb.DB;

public class MapInboundMsgActionSelector implements InboundMsgActionSelector {
	
private Map<Class, MsgAction> map = new HashMap<Class, MsgAction>();
	
	public MapInboundMsgActionSelector(PubSub pubsub, IdGenerator idGenerator, DB db) {
		map.put(Connect.class, new ConnectMsgAction());
		map.put(Sub.class, new SubMsgAction(pubsub));
		map.put(Unsub.class, new UnsubMsgAction(pubsub));
		MethodMsgAction methodMsgAction = new MethodMsgAction();
		methodMsgAction.addInvoker(new ReflectionMethodInvoker());
		methodMsgAction.addInvoker(new MongoDBMethodInvoker(db, pubsub, idGenerator) );
		map.put(Method.class, methodMsgAction);
		map.put(Ping.class, new PingMsgAction());
	}

	@Override
	public MsgAction select(Class msgClass) {
		return map.get(msgClass);
	}

}
