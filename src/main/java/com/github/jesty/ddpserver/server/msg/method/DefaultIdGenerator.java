package com.github.jesty.ddpserver.server.msg.method;

import java.util.UUID;

public class DefaultIdGenerator implements IdGenerator {

	@Override
	public String generateCollectionID(String randomSeed) {
		return UUID.fromString(randomSeed).toString();
	}

}
