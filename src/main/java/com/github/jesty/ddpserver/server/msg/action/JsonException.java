package com.github.jesty.ddpserver.server.msg.action;

public class JsonException extends RuntimeException {

	private static final long serialVersionUID = -4539210133810997973L;

	public JsonException(Exception e) {
		super(e);
	}

}
