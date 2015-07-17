package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasCollection;
import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * addedBefore (server -> client):
 * collection: string (collection name)
 * id: string (document ID)
 * fields: optional object with EJSON values
 * before: string or null (the document ID to add the document before, or null to add at the end)
 *
 */
public class AddedBefore  implements HasMsg, HasCollection {
	
	public static final String MSG = "addedBefore";
	
	private String collection;
	private String id;
	private String fields;
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
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
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
