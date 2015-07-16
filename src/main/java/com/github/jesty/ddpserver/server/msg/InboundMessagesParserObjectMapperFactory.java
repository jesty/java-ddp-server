package com.github.jesty.ddpserver.server.msg;

import org.codehaus.jackson.map.ObjectMapper;

public interface InboundMessagesParserObjectMapperFactory {

	ObjectMapper build();

}
