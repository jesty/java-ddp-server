package com.github.jesty.ddpserver.server.msg.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMethodInvoker implements MethodInvoker {

	private Map<Class<?>, Object> instanceCache = new HashMap<Class<?>, Object>();

	/* (non-Javadoc)
	 * @see com.github.jesty.ddpserver.server.msg.method.MethodInvoker#invoke(java.lang.String, java.lang.Object[])
	 * 
	 * Currently this invoker doesn't support call to method with overrun parameters. This is the first version :)
	 */
	@Override
	public Object invoke(String key, Object...params) throws NoSuchMethodException {
		int lastIndexOf = key.lastIndexOf('.');
		if(lastIndexOf <= 0){
			throw new NoSuchMethodException(key);
		}
		String className = key.substring(0, lastIndexOf);
		String methodName = key.substring(lastIndexOf + 1);
		Class<?> clazz;
		try {
			clazz = Class.forName(className);
			Object newInstance = getOrNewInstance(clazz);
			Method method = findMethod(clazz, methodName);
			return method.invoke(newInstance, params);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private Object getOrNewInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object object = instanceCache.get(clazz);
		if(object == null){
			object = clazz.newInstance();
			instanceCache.put(clazz, object);
		}
		return object;
	}

	private Method findMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName)){
				return method;
			}
		}
		throw new NoSuchMethodException(methodName);
	}

}
