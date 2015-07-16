package com.github.jesty.ddpserver.server.msg;


public class ParseMessageException extends RuntimeException {

	private static final long serialVersionUID = -8353427337407025928L;

	public ParseMessageException(Exception e) {
		super(e);
	}

}
