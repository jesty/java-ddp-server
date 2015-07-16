package com.github.jesty.ddpserver.model.rpc;

import com.github.jesty.ddpserver.model.HasMsg;

/**
 * @author davidecerbo
 * Messages:
 * updated (server -> client):
 * methods: array of strings (ids passed to 'method', all of whose writes have been reflected in data messages)
 */
public class Updated implements HasMsg {
	
	public static final String MSG = "updated";
	
	private String[] methods;

	public String[] getMethods() {
		return methods;
	}
	
	public void setMethods(String[] methods) {
		this.methods = methods;
	}
	
	@Override
	public String getMsg() {
		return MSG;
	}
}
