package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * unsub (client -> server):
 * id: string (the id passed to 'sub')
 *
 */
public class Unsub  implements HasMsg {
	
	public static final String MSG = "unsub";

	private String id;

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
