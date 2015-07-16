package com.github.jesty.ddpserver.server.msg;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JacksonOutboundMessagesFormatter<T> implements OutboundMessagesFormatter<T> {

	private ObjectMapper objectMapper;

	public JacksonOutboundMessagesFormatter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public String format(T obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
