package com.github.jesty.ddpserver.server.msg.method;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import com.github.jesty.ddpserver.model.managingdata.Added;
import com.github.jesty.ddpserver.model.managingdata.Changed;
import com.github.jesty.ddpserver.model.managingdata.Removed;
import com.github.jesty.ddpserver.server.msg.pubsub.Event;
import com.github.jesty.ddpserver.server.msg.pubsub.PubSub;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;

public class MongoDBMethodInvoker implements MethodInvoker {
	
	private PubSub pubSub;
	private IdGenerator idGenerator;
	private DB db;

	public MongoDBMethodInvoker(DB db, PubSub pubSub, IdGenerator idGenerator){
		this.db = db;
		this.pubSub = pubSub;
		this.idGenerator = idGenerator;
	}

	@Override
	public Object invoke(String key, final Object... params) throws NoSuchMethodException {
		int indexOf = key.lastIndexOf("/");
		if(indexOf > 0){
			String action = key.substring(indexOf + 1);
			final String collectionName = key.substring(1, indexOf);
			final DBCollection collection = db.getCollection(collectionName);
			switch (action) {
			case "insert":
				insert(collection, params);
				break;
			case "update":
				update(collection, params);
				break;
			case "delete":
				delete(collection, params);
				break;
			}
			return null;
		} else {
			throw new NoSuchMethodException(key);
		}
	}

	private void delete(final DBCollection collection, final Object[] params) {
		Map map = (Map) params[0];
		final String objId = (String) map.get("_id");
		final BasicDBObject basicDBObject = new BasicDBObject(map);
		basicDBObject.put("_id", objId);
		collection.remove(basicDBObject);
		pubSub.fireEvent(new Event(new Removed(){{
			setCollection(collection.getName());
			setId(objId);
		}}));
	}

	private void update(final DBCollection collection, final Object[] params) {
		Map map = (Map) params[0];
		final String objId = (String) map.get("_id");
		final Map setMap = (Map) params[1];
		WriteResult update = collection.update(new BasicDBObject("_id", objId), new BasicDBObject(setMap));
		pubSub.fireEvent(new Event(new Changed(){{
			setCollection(collection.getName());
			setFields(new LinkedList<Object>(setMap.values()));
			setId(objId);
		}}));
	}

	private void insert(final DBCollection collection, final Object... params) {
		final String id = idGenerator.generateCollectionID(null);
		Map map = (Map) params[0];
		BasicDBObject basicDBObject = new BasicDBObject(map);
		basicDBObject.put("_id", id);
		collection.insert(basicDBObject);
		pubSub.fireEvent(new Event(new Added(){{
			setCollection(collection.getName());
			setFields(Arrays.asList(params));
			setId(id);
		}}));
	}


}
