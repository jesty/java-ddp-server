package com.github.jesty.ddpserver.server.msg;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

import com.github.jesty.ddpserver.model.HasMsg;


public class JacksonInboundMessagesParser implements InboundMessagesParser<HasMsg> {

	private InboundMessagesParserObjectMapperFactory objectMapperFactory;
	private ObjectMapper objectMapper;
	
	public JacksonInboundMessagesParser(InboundMessagesParserObjectMapperFactory objectMapperFactory) {
		this.objectMapperFactory = objectMapperFactory;
	}
	
	public void init(){
		this.objectMapper = this.objectMapperFactory.build();
	}
	
	@Override
	public HasMsg parse(String json) {
		try {
			return objectMapper.readValue(json, HasMsg.class);
		} catch (IOException e) {
			throw new ParseMessageException(e);
		}
	}

}
