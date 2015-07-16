package com.github.jesty.ddpserver.server.msg;

public interface InboundMessagesParser<T> {
	
	T parse(String json);

	void init();

}
