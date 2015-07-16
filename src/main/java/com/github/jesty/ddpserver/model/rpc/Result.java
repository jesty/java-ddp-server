package com.github.jesty.ddpserver.model.rpc;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 * Messages:
 * result (server -> client):
 * id: string (the id passed to 'method')
 * error: optional Error (an error thrown by the method (or method-not-found)
 * result: optional EJSON item (the return value of the method, if any)
 */
public class Result implements HasMsg {
	
	public static final String MSG = "result";
	
	private String id;
	private com.github.jesty.ddpserver.model.error.Error error;
	private Object result;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public com.github.jesty.ddpserver.model.error.Error getError() {
		return error;
	}
	public void setError(com.github.jesty.ddpserver.model.error.Error error) {
		this.error = error;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	@Override
	public String getMsg() {
		return MSG;
	}
	
	
}
