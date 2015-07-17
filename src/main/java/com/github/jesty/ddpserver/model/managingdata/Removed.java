package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasCollection;
import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * removed (server -> client):
 * collection: string (collection name)
 * id: string (document ID)
 *
 */
public class Removed  implements HasMsg, HasCollection {
	
	public static final String MSG = "removed";
	
	private String collection;
	private String id;
	
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
	@Override
	public String getMsg() {
		return MSG;
	}

}
