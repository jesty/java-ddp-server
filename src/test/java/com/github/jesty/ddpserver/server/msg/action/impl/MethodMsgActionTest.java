package com.github.jesty.ddpserver.server.msg.action.impl;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import javax.websocket.RemoteEndpoint.Async;
import javax.websocket.RemoteEndpoint.Basic;

import org.junit.Before;
import org.junit.Test;

import com.github.jesty.ddpserver.model.rpc.Method;
import com.github.jesty.ddpserver.server.msg.method.ReflectionMethodInvoker;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class MethodMsgActionTest {
	
	private MethodMsgAction action;
	
	@Before
	public void init(){
		action = new MethodMsgAction();
		action.addInvoker(new ReflectionMethodInvoker());
		
	}

	@Test
	public void testExecuteNotFound() throws Exception {
		MockSession mockSession = new MockSession();
		DDPSession session = new DDPSession(mockSession);
		Method object = new Method(){{
			setId("10");
			setMethod("testMethod");
			setParams(Arrays.asList("test"));
		}};
		action.execute(object, session);
		assertEquals("{\"id\":\"10\",\"error\":{\"error\":\"Method 'testMethod' not found\",\"reason\":null,\"details\":null,\"msg\":\"error\"},\"result\":null,\"msg\":\"result\"}", mockSession.lastText.get(0));
	}
	
	@Test
	public void testExecute() throws Exception {
		MockSession mockSession = new MockSession();
		DDPSession session = new DDPSession(mockSession);
		Method object = new Method(){{
			setId("10");
			setMethod("com.github.jesty.ddpserver.server.msg.method.TestClass.testMethod");
			setParams(Arrays.asList("test"));
		}};
		action.execute(object, session);
		assertEquals("{\"id\":\"10\",\"error\":null,\"result\":\"test yes!\",\"msg\":\"result\"}", mockSession.lastText.get(0));
		assertEquals("{\"methods\":[\"10\"],\"msg\":\"updated\"}", mockSession.lastText.get(1));
	}

}
