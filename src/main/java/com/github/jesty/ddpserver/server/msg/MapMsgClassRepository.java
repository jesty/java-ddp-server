package com.github.jesty.ddpserver.server.msg;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.model.connection.Connect;
import com.github.jesty.ddpserver.model.connection.Connected;
import com.github.jesty.ddpserver.model.connection.Failed;
import com.github.jesty.ddpserver.model.hearthbit.Ping;
import com.github.jesty.ddpserver.model.hearthbit.Pong;
import com.github.jesty.ddpserver.model.managingdata.Added;
import com.github.jesty.ddpserver.model.managingdata.AddedBefore;
import com.github.jesty.ddpserver.model.managingdata.Changed;
import com.github.jesty.ddpserver.model.managingdata.MovedBefore;
import com.github.jesty.ddpserver.model.managingdata.Nosub;
import com.github.jesty.ddpserver.model.managingdata.Ready;
import com.github.jesty.ddpserver.model.managingdata.Removed;
import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.model.rpc.Method;
import com.github.jesty.ddpserver.model.rpc.Result;
import com.github.jesty.ddpserver.model.rpc.Updated;

public class MapMsgClassRepository implements MsgClassRepository {
	
	private Map<String, Class<? extends HasMsg>> map = new HashMap<String, Class<? extends HasMsg>>();
	
	public MapMsgClassRepository() {
		map.put(Connect.MSG, Connect.class);
		map.put(Connected.MSG, Connected.class);
		map.put(Failed.MSG, Failed.class);
		map.put(com.github.jesty.ddpserver.model.error.Error.MSG, com.github.jesty.ddpserver.model.error.Error.class);
		map.put(Ping.MSG, Ping.class);
		map.put(Pong.MSG, Pong.class);
		map.put(Added.MSG, Added.class);
		map.put(AddedBefore.MSG, AddedBefore.class);
		map.put(Changed.MSG, Changed.class);
		map.put(MovedBefore.MSG, MovedBefore.class);
		map.put(Nosub.MSG, Nosub.class);
		map.put(Ready.MSG, Ready.class);
		map.put(Removed.MSG, Removed.class);
		map.put(Sub.MSG, Sub.class);
		map.put(Unsub.MSG, Unsub.class);
		map.put(Method.MSG, Method.class);
		map.put(Result.MSG, Result.class);
		map.put(Updated.MSG, Updated.class);
	}

	@Override
	public Class<? extends HasMsg> classFor(String msg) {
		return map.get(msg);
	}
	
	@Override
	public String msgFor(Class<? extends HasMsg> clazz){
		Set<Entry<String,Class<? extends HasMsg>>> entrySet = map.entrySet();
		for (Entry<String, Class<? extends HasMsg>> entry : entrySet) {
			if(entry.getValue().equals(clazz)){
				return entry.getKey();
			}
		}
		return null;
	}
	
	@Override
	public Collection<Class<? extends HasMsg>> getAllClasses(){
		return map.values();
	}

}
