package com.github.jesty.ddpserver.server.msg.objectmapper;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.std.StdDeserializer;
import org.codehaus.jackson.node.ObjectNode;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.server.msg.MsgClassRepository;

public class DDPCustomDeserializer<T extends HasMsg> extends StdDeserializer<T> {

	private static final String MSG_KEY = "msg";
	private MsgClassRepository msgClassRepository;
	private ObjectMapper basicObjectMapper;

	public DDPCustomDeserializer(MsgClassRepository msgClassRepository, ObjectMapper basicObjectMapper) {
		super(HasMsg.class);
		this.msgClassRepository = msgClassRepository;
		this.basicObjectMapper = basicObjectMapper;
	}

	@Override
	public T deserialize(JsonParser jp, DeserializationContext ctxt)  throws IOException, JsonProcessingException {
		ObjectCodec codec = jp.getCodec();
		ObjectNode node = (ObjectNode) codec.readTree(jp);
		JsonNode jsonNode = node.get(MSG_KEY);
		if(jsonNode != null){
			String msg = jsonNode.asText();
			node.remove(MSG_KEY);
			Class<T> classFor =  (Class<T>) msgClassRepository.classFor(msg);
			T result = null;
			if(classFor != null){
				result = this.basicObjectMapper.readValue(node, classFor);
			}
			return result;
		} else {
			return null;
		}
	}
}
