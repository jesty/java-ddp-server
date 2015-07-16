package com.github.jesty.ddpserver.server.msg;

import java.util.Collection;

import com.github.jesty.ddpserver.model.HasMsg;

public interface MsgClassRepository {

	Class<? extends HasMsg> classFor(String msg);

	String msgFor(Class<? extends HasMsg> clazz);

	Collection<Class<? extends HasMsg>> getAllClasses();

}
