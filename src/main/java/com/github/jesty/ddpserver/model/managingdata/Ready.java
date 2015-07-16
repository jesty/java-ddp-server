package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * ready (server -> client):
 * subs: array of strings (ids passed to 'sub' which have sent their initial batch of data)
 *
 */
public class Ready  implements HasMsg {
	
	public static final String MSG = "ready";
	
	private String[] subs;

	public String[] getSubs() {
		return subs;
	}
	public void setSubs(String[] subs) {
		this.subs = subs;
	}
	@Override
	public String getMsg() {
		return MSG;
	}
	
	

}
