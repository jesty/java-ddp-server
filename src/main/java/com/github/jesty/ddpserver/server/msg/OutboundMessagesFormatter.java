package com.github.jesty.ddpserver.server.msg;

public interface OutboundMessagesFormatter<T> {

	String format(T obj);
	
}
