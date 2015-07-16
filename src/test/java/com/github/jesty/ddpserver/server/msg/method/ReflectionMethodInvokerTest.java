package com.github.jesty.ddpserver.server.msg.method;

import org.junit.Assert;
import org.junit.Test;


public class ReflectionMethodInvokerTest {
	
	@Test(expected=NoSuchMethodException.class)
	public void testInvokeFail() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.testMethodNotExists";
		Object invoke = invoker.invoke(method, "test");
		Assert.assertEquals("No params yes!", invoke);
	}
	
	@Test
	public void testInvokeVoid() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.testMethodVoid";
		Object invoke = invoker.invoke(method, "test");
		Assert.assertNull(invoke);
	}
	
	@Test
	public void testInvokeSimple() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.testMethod";
		Object invoke = invoker.invoke(method, "test");
		Assert.assertEquals("test yes!", invoke);
	}
	
	@Test
	public void testInvokeNoParameters() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.testMethodNoParams";
		Object invoke = invoker.invoke(method);
		Assert.assertEquals("No params yes!", invoke);
	}
	
	@Test
	public void testInvokeSimpleStatic() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.staticTestMethod";
		Object invoke = invoker.invoke(method, "test");
		Assert.assertEquals("test yes static!", invoke);
	}
	
	@Test
	public void testInvokeInt() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.add";
		Object invoke = invoker.invoke(method, 1, 2);
		Assert.assertEquals(3, invoke);
	}
	
	@Test
	public void testInvokeSubtypes() throws Exception {
		ReflectionMethodInvoker invoker = new ReflectionMethodInvoker();
		String method = "com.github.jesty.ddpserver.server.msg.method.TestClass.sub";
		Object invoke = invoker.invoke(method, 5, 3);
		Assert.assertEquals(2, invoke);
	}
	
}
