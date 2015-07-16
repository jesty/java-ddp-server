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

public class MapInboundMsgActionSelector implements InboundMsgActionSelector {
	
private Map<Class, MsgAction> map = new HashMap<Class, MsgAction>();
	
	public MapInboundMsgActionSelector() {
		map.put(Connect.class, new ConnectMsgAction());
		map.put(Sub.class, new SubMsgAction());
		map.put(Unsub.class, new UnsubMsgAction());
		map.put(Method.class, new MethodMsgAction());
		map.put(Ping.class, new PingMsgAction());
	}

	@Override
	public MsgAction select(Class msgClass) {
		return map.get(msgClass);
	}

}
