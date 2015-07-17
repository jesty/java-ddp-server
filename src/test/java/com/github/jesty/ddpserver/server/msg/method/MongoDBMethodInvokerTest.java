package com.github.jesty.ddpserver.server.msg.method;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.jesty.ddpserver.model.HasMsg;
import com.github.jesty.ddpserver.model.managingdata.Sub;
import com.github.jesty.ddpserver.model.rpc.Method;
import com.github.jesty.ddpserver.server.msg.InboundMessagesParser;
import com.github.jesty.ddpserver.server.msg.InboundMessagesParserObjectMapperFactoryImpl;
import com.github.jesty.ddpserver.server.msg.JacksonInboundMessagesParser;
import com.github.jesty.ddpserver.server.msg.action.impl.MethodMsgAction;
import com.github.jesty.ddpserver.server.msg.action.impl.MockSession;
import com.github.jesty.ddpserver.server.msg.pubsub.MapPubSub;
import com.github.jesty.ddpserver.server.msg.session.DDPSession;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public class MongoDBMethodInvokerTest {

	private static final int PORT = 12345;
	private static final String HOST = "localhost";

	private MethodMsgAction action;
	private DDPSession session;
	private MockSession mockSession;
	private DBCollection collection;
	private static MongodExecutable mongodExecutable;

	@Before
	public void init() throws IOException{
		action = new MethodMsgAction();
		MapPubSub pubSub = new MapPubSub();
		DB db = initMongoDB();
		collection = db.getCollection("customer");
		collection.drop();
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("_id", "testid");
		basicDBObject.put("testField", "pippo");
		collection.insert(basicDBObject);
		action.addInvoker(new MongoDBMethodInvoker(db, pubSub, new IdGenerator() {
			@Override
			public String generateCollectionID(String randomSeed) {
				return "fixed";
			}
		}));
		mockSession = new MockSession();
		session = new DDPSession(mockSession);
		pubSub.sub(new Sub(){{setName("customer");}}, session);
	}

	@BeforeClass
	public static void startMongoDB() throws UnknownHostException, IOException{
		MongodStarter starter = MongodStarter.getDefaultInstance();
		IMongodConfig mongodConfig = new MongodConfigBuilder()
		.version(Version.Main.PRODUCTION)
		.net(new Net(PORT, Network.localhostIsIPv6()))
		.build();

		mongodExecutable = null;
		try {
			mongodExecutable = starter.prepare(mongodConfig);
			MongodProcess mongod = mongodExecutable.start();

		} catch(Exception e) {
			if (mongodExecutable != null)
				mongodExecutable.stop();
		}
	}

	@AfterClass
	public static void stopDB(){
		if (mongodExecutable != null){
			mongodExecutable.stop();
		}
	}

	private DB initMongoDB() throws IOException {
		MongoClient mongoClient;
		mongoClient = new MongoClient( HOST , PORT );
		DB db = mongoClient.getDB("test");
		return db;
	}

	@Test
	public void testInvokeDelete() throws Exception {
		InboundMessagesParser<HasMsg> inboundMessagesParser = new JacksonInboundMessagesParser(new InboundMessagesParserObjectMapperFactoryImpl());
		inboundMessagesParser.init();
		assertEquals(1, collection.find(new BasicDBObject("_id", "testid")).size());
		String message = "{\"msg\":\"method\",\"method\":\"/customer/delete\",\"params\":[{\"_id\":\"testid\"}],\"id\":\"2\"}";
		Method object = (Method) inboundMessagesParser.parse(message);
		action.execute(object, session);
		assertEquals(0, collection.find(new BasicDBObject("_id", "testid")).size());
		assertEquals("{\"collection\":\"customer\",\"id\":\"testid\",\"msg\":\"removed\"}", mockSession.lastText.get(0));
		assertEquals("{\"id\":\"2\",\"error\":null,\"result\":null,\"msg\":\"result\"}", mockSession.lastText.get(1));
		assertEquals("{\"methods\":[\"2\"],\"msg\":\"updated\"}", mockSession.lastText.get(2));
	}

	@Test
	//Should be useful to test more update queries like $inc
	public void testInvokeUpdate() throws Exception {
		InboundMessagesParser<HasMsg> inboundMessagesParser = new JacksonInboundMessagesParser(new InboundMessagesParserObjectMapperFactoryImpl());
		inboundMessagesParser.init();

		assertEquals(1, collection.find(new BasicDBObject("_id", "testid")).size());
		String message = "{\"msg\":\"method\",\"method\":\"/customer/update\",\"params\":[{\"_id\":\"fixed\"},{\"$set\":{\"testField\":\"pippo\"}},{\"validationContext\":\"updateCustomerForm\"}],\"id\":\"2\"}";
		Method object = (Method) inboundMessagesParser.parse(message);
		action.execute(object, session);

		assertEquals("pippo", collection.find(new BasicDBObject("_id", "testid")).next().get("testField"));

		assertEquals("{\"collection\":\"customer\",\"id\":\"fixed\",\"fields\":[{\"testField\":\"pippo\"}],\"cleared\":null,\"msg\":\"changed\"}", mockSession.lastText.get(0));
		assertEquals("{\"id\":\"2\",\"error\":null,\"result\":null,\"msg\":\"result\"}", mockSession.lastText.get(1));
		assertEquals("{\"methods\":[\"2\"],\"msg\":\"updated\"}", mockSession.lastText.get(2));
	}

	@Test
	public void testInvokeFull() throws Exception {
		InboundMessagesParser<HasMsg> inboundMessagesParser = new JacksonInboundMessagesParser(new InboundMessagesParserObjectMapperFactoryImpl());
		inboundMessagesParser.init();
		String message = "{\"msg\":\"method\",\"method\":\"/customer/insert\",\"params\":[{\"name\":\"aaaaa\",\"fip\":\"No\",\"type\":\"test\",\"note\":\"test\",\"openingTimes\":{\"MONDAY\":[{\"time\":\"si\"},{\"time\":\"no\"}]}}],\"id\":\"2\",\"randomSeed\":\"70c88aa0cf95d711e947\"}";
		Method object = (Method) inboundMessagesParser.parse(message);
		action.execute(object, session);

		assertEquals("{\"collection\":\"customer\",\"id\":\"fixed\",\"fields\":[{\"name\":\"aaaaa\",\"fip\":\"No\",\"type\":\"test\",\"note\":\"test\",\"openingTimes\":{\"MONDAY\":[{\"time\":\"si\"},{\"time\":\"no\"}]}}],\"msg\":\"added\"}", mockSession.lastText.get(0));
		assertEquals("{\"id\":\"2\",\"error\":null,\"result\":null,\"msg\":\"result\"}", mockSession.lastText.get(1));
		assertEquals("{\"methods\":[\"2\"],\"msg\":\"updated\"}", mockSession.lastText.get(2));
	}

}
