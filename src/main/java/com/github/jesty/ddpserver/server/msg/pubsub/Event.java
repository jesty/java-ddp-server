package com.github.jesty.ddpserver.server.msg.pubsub;

import com.github.jesty.ddpserver.model.HasCollection;
import com.github.jesty.ddpserver.model.HasMsg;

public class Event {
	
	private HasMsg msg;
	private String collection;
	
	public Event(HasCollection msg){
		try {
			this.msg = (HasMsg) msg;
		} catch (ClassCastException e) {
			throw new RuntimeException("To create a new Event, must implements also HasMsg interface.");
		}
		this.collection = msg.getCollection();
	}
	
	public Event(HasMsg msg, String collection) {
		this.msg = msg;
		this.collection = collection;
	}
	
	public HasMsg getMsg() {
		return msg;
	}
	
	public String getCollection() {
		return collection;
	}
	
}
