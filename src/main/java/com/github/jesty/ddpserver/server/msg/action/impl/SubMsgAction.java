package com.github.jesty.ddpserver.server.msg.action.impl;

import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class SubMsgAction implements MsgAction<Sub> {
	
//	private static Map<String, Subject<Object, Object>> subs = new HashMap<String, Subject<Object, Object>>();

	@Override
	public void execute(Sub sub, DDPSession session) {
//		String name = sub.getName();
//		Subject<Object, Object> bus = subs.get(name);
//		if(bus == null){
//			bus = new SerializedSubject<>(PublishSubject.create());
//			subs.put(name, bus);
//		}
//		bus.subscribe(new Action1<String>() {
//		    @Override
//		    public void call(String s) {
//		        System.out.println("PubSub 2: " + s);
//		    }
//		});
		
	}

}
