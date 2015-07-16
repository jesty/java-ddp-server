package com.github.jesty.ddpserver.server.msg.method;


public interface MethodInvoker {

	Object invoke(String key, Object... params) throws NoSuchMethodException; 

}
