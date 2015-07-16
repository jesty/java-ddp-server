package com.github.jesty.ddpserver.server.msg;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.model.hearthbit.Ping;
import com.github.jesty.ddpserver.model.hearthbit.Pong;

public class JacksonInboundMessagesParserTest {
	
	private JacksonInboundMessagesParser parser;
	
	@Before
	public void init(){
		InboundMessagesParserObjectMapperFactory objectMapperFactory = new InboundMessagesParserObjectMapperFactoryImpl();
		parser = new JacksonInboundMessagesParser(objectMapperFactory); 
		parser.init();
	}
	
	@Test
	public void testParseSuccess() throws Exception {
		
		String json = "{\"id\":\"test\",\"msg\":\"ping\"}";
		HasMsg readValue = parser.parse(json);
		
		Ping p = new Ping();
		p.setId("test");

		Assert.assertEquals(p, readValue);
		
		String jsonpong = "{\"id\":\"test\",\"msg\":\"pong\"}";
		HasMsg readValuepong = parser.parse(jsonpong);
		
		Pong pong = new Pong();
		pong.setId("test");

		Assert.assertEquals(pong, readValuepong);
	}

	@Test(expected=ParseMessageException.class)
	public void testParseNotJson() throws Exception {
		parser.parse("sasa");
	}
	
	@Test
	public void testParseNotMsg() throws Exception {
		Assert.assertNull(parser.parse("{\"id\":\"test\",\"noMsg\":\"pong\"}"));
	}

}
