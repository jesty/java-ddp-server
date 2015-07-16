package com.github.jesty.ddpserver.model.connection;

import com.github.jesty.ddpserver.model.HasMsg;


/**
 * @author davidecerbo
 * 
 * Messages:
 * connect (client -> server)
 * session: string (if trying to reconnect to an existing DDP session)
 * version: string (the proposed protocol version)
 * support: array of strings (protocol versions supported by the client, in order of preference)
 *
 */
public class Connect implements HasMsg {
	
	public static final String MSG = "connect";

	private String session;
	private String version;
	private String[] support;
	
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String[] getSupport() {
		return support;
	}
	public void setSupport(String[] support) {
		this.support = support;
	}
	
	@Override
	public String getMsg() {
		return MSG;
	}
	
}
