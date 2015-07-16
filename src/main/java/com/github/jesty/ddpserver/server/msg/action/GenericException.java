package com.github.jesty.ddpserver.server.msg.action;

public class GenericException extends RuntimeException {
	
	private static final long serialVersionUID = 279001182034486455L;

	public GenericException(Exception e){
		super(e);
	}

}
