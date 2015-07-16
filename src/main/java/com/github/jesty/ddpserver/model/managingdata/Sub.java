package com.github.jesty.ddpserver.model.managingdata;

import java.util.Map;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * sub (client -> server):
 * id: string (an arbitrary client-determined identifier for this subscription)
 * name: string (the name of the subscription)
 * params: optional array of EJSON items (parameters to the subscription)
 *
 */
public class Sub  implements HasMsg {
	
	public static final String MSG = "sub";

	private String id;
	private String name;
	private Map<String, Object>[] params;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, Object>[] getParams() {
		return params;
	}
	public void setParams(Map<String, Object>[] params) {
		this.params = params;
	}
	
	@Override
	public String getMsg() {
		return MSG;
	}
	
}
