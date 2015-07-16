package com.github.jesty.ddpserver.server.msg.action.impl;

import java.util.LinkedList;
import java.util.List;

import com.github.jesty.ddpserver.model.rpc.Method;
import com.github.jesty.ddpserver.model.rpc.Result;
import com.github.jesty.ddpserver.model.rpc.Updated;
import com.github.jesty.ddpserver.server.msg.action.MsgAction;
import com.github.jesty.ddpserver.server.msg.method.MethodInvoker;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;

public class MethodMsgAction implements MsgAction<Method> {

	private List<MethodInvoker> invokers = new LinkedList<MethodInvoker>();

	@Override
	public void execute(final Method object, DDPSession session) {
		Result result = null;
		Updated updated = null;
		for (MethodInvoker methodInvoker : invokers) {
			try{
				Object invokationResult = invoke(object, methodInvoker);
				if(invokationResult != null){
					result = new Result();
					result.setResult(invokationResult);
					updated = new Updated(){{
						setMethods(new String[]{object.getId()});
					}};
					break;
				}
			} catch(NoSuchMethodException e){
				//method not found, try next invoker
				continue;
			} catch(Exception e){
				result = new Result();
				com.github.jesty.ddpserver.model.error.Error error = new com.github.jesty.ddpserver.model.error.Error();
				error.setError(e.getClass().toString());
				error.setReason(e.getMessage());
				result.setError(error);
				break;
			}
		}
		if(result == null){
			result = new Result();
			com.github.jesty.ddpserver.model.error.Error error = new com.github.jesty.ddpserver.model.error.Error();
			error.setError("Method '" + object.getMethod()  + "' not found");
			result.setError(error);
		}
		result.setId(object.getId());
		session.sendMsg(result);
		//send updated message, always, after method invocation. In future the developer could decide when send updated message
		if(updated != null){
			session.sendMsg(updated);
		}
	}



	private Object invoke(Method object, MethodInvoker methodInvoker)
			throws NoSuchMethodException {
		List<?> params = object.getParams();
		Object[] array = null;
		if(params != null){
			array = params.toArray();
		}
		Object invokationResult = methodInvoker.invoke(object.getMethod(), array);
		return invokationResult;
	}

	

	public void addInvoker(MethodInvoker invoker){
		this.invokers.add(invoker);
	}

	public void setInvokers(List<MethodInvoker> invokers) {
		this.invokers = invokers;
	}

}
