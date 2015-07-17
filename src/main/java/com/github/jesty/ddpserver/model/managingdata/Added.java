package com.github.jesty.ddpserver.model.managingdata;

import java.util.List;

import com.github.jesty.ddpserver.model.HasCollection;
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
public class Added  implements HasMsg, HasCollection {
	
	public static final String MSG = "added";
	
	private String collection;
	private String id;
	private List<?> fields;
	
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
	public void setFields(List<?> fields) {
		this.fields = fields;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
