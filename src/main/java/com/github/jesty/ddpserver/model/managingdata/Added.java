package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * added (server -> client):
 * collection: string (collection name)
 * id: string (document ID)
 * fields: optional object with EJSON values
 *
 */
public class Added  implements HasMsg {
	
	public static final String MSG = "added";
	
	private String added;
	private String collection;
	private String id;
	private String fields;
	
	public String getAdded() {
		return added;
	}
	public void setAdded(String added) {
		this.added = added;
	}
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
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
