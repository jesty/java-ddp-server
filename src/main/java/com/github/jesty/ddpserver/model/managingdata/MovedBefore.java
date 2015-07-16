package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * movedBefore (server -> client):
 * collection: string
 * id: string (the document ID)
 * before: string or null (the document ID to move the document before, or null to move to the end)
 *
 */
public class MovedBefore  implements HasMsg {
	
	public static final String MSG = "movedBefore";
	
	private String collection;
	private String id;
	private String before;
	
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
