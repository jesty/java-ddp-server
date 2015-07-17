package com.github.jesty.ddpserver.model.managingdata;

import java.util.List;

import com.github.jesty.ddpserver.model.HasCollection;
import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * changed (server -> client):
 * collection: string (collection name)
 * id: string (document ID)
 * fields: optional object with EJSON values
 * cleared: optional array of strings (field names to delete)
 *
 */
public class Changed  implements HasMsg, HasCollection {
	
	public static final String MSG = "changed";
	
	private String collection;
	private String id;
	private List<?> fields;
	private String cleared;
	
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
	public List<?> getFields() {
		return fields;
	}
	public void setFields(List<?> list) {
		this.fields = list;
	}
	public String getCleared() {
		return cleared;
	}
	public void setCleared(String cleared) {
		this.cleared = cleared;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
