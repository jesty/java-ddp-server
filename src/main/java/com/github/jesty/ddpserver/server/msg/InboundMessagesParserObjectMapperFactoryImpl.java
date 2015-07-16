package com.github.jesty.ddpserver.server.msg;

import java.util.Collection;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.server.msg.objectmapper.DDPCustomDeserializer;

public class InboundMessagesParserObjectMapperFactoryImpl implements InboundMessagesParserObjectMapperFactory {

	@Override
	public ObjectMapper build() {
		ObjectMapper objectMapper = new ObjectMapper();
		MsgClassRepository msgClassRepository = new MapMsgClassRepository();
		SimpleModule module = new SimpleModule("DDP", new Version(1, 0, 0, null));  
		ObjectMapper basicObjectMapper = new ObjectMapper();
		DDPCustomDeserializer ddpCustomDeserializer = new DDPCustomDeserializer(msgClassRepository, basicObjectMapper);
	   
		Collection<Class<? extends HasMsg>> allClasses = msgClassRepository.getAllClasses();
		for (Class<? extends HasMsg> class1 : allClasses) {
			configureModule(class1, module, ddpCustomDeserializer);
		}
		objectMapper.registerModule(module);
		return objectMapper;
	}

	private void configureModule(Class<? extends HasMsg> clazz, SimpleModule module, DDPCustomDeserializer ddpCustomDeserializer) {
		module.addDeserializer(clazz, ddpCustomDeserializer);
		module.addAbstractTypeMapping(HasMsg.class, clazz);
	}

}
