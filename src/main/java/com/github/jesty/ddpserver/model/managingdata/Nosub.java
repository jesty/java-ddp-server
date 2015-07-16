package com.github.jesty.ddpserver.model.managingdata;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 *
 * Messages:
 * nosub (server -> client):
 * id: string (the id passed to 'sub')
 * error: optional Error (an error raised by the subscription as it concludes, or sub-not-found)
 *
 */
public class Nosub  implements HasMsg {
	
	public static final String MSG = "nosub";
	
	private String id;
	private Error error;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Error getError() {
		return error;
	}
	public void setError(Error error) {
		this.error = error;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
