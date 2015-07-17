package com.github.jesty.ddpserver.server.msg.pubsub;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.model.managingdata.Unsub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class MapPubSub implements PubSub {

	private static Map<String,List<SubscriptionInfo>> collectionToSubscription = new HashMap<String, List<SubscriptionInfo>>();

	@Override
	public void sub(Sub sub, DDPSession session) {
		collectionToSubscription.put(sub.getName(), buildSubscriptionInfo(sub, session));
	}

	@Override
	public void unsub(Unsub unsub, DDPSession session) {
		Set<Entry<String,List<SubscriptionInfo>>> entrySet = collectionToSubscription.entrySet();
		for (Entry<String, List<SubscriptionInfo>> entry : entrySet) {
			List<SubscriptionInfo> list = entry.getValue();
			Iterator<SubscriptionInfo> iterator = list.iterator();
			while (iterator.hasNext()) {
				SubscriptionInfo subscriptionInfo = (SubscriptionInfo) iterator.next();
				boolean sessionEq = subscriptionInfo.getSession().equals(session);
				boolean idEq = subscriptionInfo.getSub().getId().equals(unsub.getId());
				if(sessionEq && idEq){
					iterator.remove();
				}
			}
		}
	}

	private List<SubscriptionInfo> buildSubscriptionInfo(Sub sub, DDPSession session) {
		List<SubscriptionInfo> list = collectionToSubscription.get(sub.getName());
		if(list == null ){
			list = new LinkedList<SubscriptionInfo>();
		}
		list.add(new SubscriptionInfo(sub, session));
		return list;
	}

	@Override
	public void fireEvent(Event event) {
		List<SubscriptionInfo> list = collectionToSubscription.get(event.getCollection());
		if(list != null){
			for (SubscriptionInfo subscriptionInfo : list) {
				subscriptionInfo.getSession().sendMsg(event.getMsg());
			}
		}
	}

}
