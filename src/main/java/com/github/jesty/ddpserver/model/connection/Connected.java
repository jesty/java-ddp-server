package com.github.jesty.ddpserver.model.connection;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 * 
 * Messages:
 * connected (server->client)
 * session: string (an identifier for the DDP session)
 *
 */
public class Connected  implements HasMsg {
	
	public static final String MSG = "connected";
	private String session;
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	@Override
	public String getMsg() {
		return MSG;
	}

}
