package com.github.jesty.ddpserver.model.connection;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 * 
 * Messages:
 * connected (server->client)
 * session: string (an identifier for the DDP session)
 * failed (server->client)
 * version: string (a suggested protocol version to connect with)
 *
 */
public class Failed  implements HasMsg {
	
	public static final String MSG = "failed";
	
	private String version;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getMsg() {
		return MSG;
	}
}
