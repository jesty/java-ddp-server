package com.github.jesty.ddpserver.model.rpc;

import java.util.List;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 * Messages:
 * method (client -> server):
 * method: string (method name)
 * params: optional array of EJSON items (parameters to the method)
 * id: string (an arbitrary client-determined identifier for this method call)
 * randomSeed: optional JSON value (an arbitrary client-determined seed for pseudo-random generators)
 *
 */
public class Method  implements HasMsg {
	
	public static final String MSG = "method";
	
	private String method;
	private List<?> params;
	private String id;
	private String randomSeed;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public List<?> getParams() {
		return params;
	}
	public void setParams(List<?> params) {
		this.params = params;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRandomSeed() {
		return randomSeed;
	}
	public void setRandomSeed(String randomSeed) {
		this.randomSeed = randomSeed;
	}

	@Override
	public String getMsg() {
		return MSG;
	}

}
