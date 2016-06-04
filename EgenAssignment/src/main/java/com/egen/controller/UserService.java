package com.egen.controller;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.egen.model.Result;
import com.egen.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * This class will implement the actual logic for given services. It will get
 * the connection from Mongo DB and it will hit the data base and get the
 * required results. One method in this class is being called by UserController
 * class. That in turn will be called from web service clients
 * 
 * @author VENKY
 *
 */
public class UserService {
	private static final Logger logger = Logger.getLogger(UserService.class);

	/**
	 * getUser method will return the user object in JSON format for given USER
	 * ID
	 * 
	 * @param id
	 * @return
	 */
	public BasicDBObject getUser(String id) {
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("userdata");
		BasicDBObject userObj = null;
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		DBCursor cursor = coll.find(whereQuery);
		if (cursor.hasNext()) {
			userObj = (BasicDBObject) cursor.next();
		} else {
			throw new IllegalArgumentException("No user with id '" + id + "' found");
		}
		return userObj;
	}

	/**
	 * This method will be used to create an user in data base with the given JSON values.
	 * @param obj
	 * @return
	 */
	public Result createUser(User obj) {
		Result result = new Result();
		result.setStatus("SUCCESS");
		String id = (String)obj.get("id");
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("userdata");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		DBCursor cursor = coll.find(whereQuery);
		if (cursor.hasNext()) {
			result.setStatus("FAILURE");
			throw new IllegalArgumentException("There is already one record with this id " + id );
		} else {
			coll.insert(obj);
		}
		
		return result;
	}
	/**
	 * This method will return all the users that are presented in the database. 
	 * @return
	 */
	public ArrayList<BasicDBObject> getUsers() {
		ArrayList<BasicDBObject> list = new ArrayList<BasicDBObject>();
		MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
		DB db = dbSingleton.getTestdb();
		DBCollection coll = db.getCollection("userdata");
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			BasicDBObject userObj = (BasicDBObject) cursor.next();
			list.add(userObj);
		}
		return list;

	}
	/**
	 * This method will update the given user object if it is already present in the data base. 
	 * otherwise it will return 404 status to the user who is requesting
	 * @param userObj
	 * @return
	 */
	public Result updateUser(User userObj) {
		Result result = new Result();
		result.setStatus("SUCCESS");
		String id = (String) userObj.get("id");
		if (id != null) {
			MongoDBSingleton dbSingleton = MongoDBSingleton.getInstance();
			DB db = dbSingleton.getTestdb();
			DBCollection coll = db.getCollection("userdata");
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("id", id);
			DBCursor cursor = coll.find(whereQuery);
			if (cursor.hasNext()) {
				coll.update(whereQuery, userObj);
			} else {
				result.setStatus("FAILURE");
				throw new IllegalArgumentException("No user with id '" + id + "' found");
			}
		} else {
			result.setStatus("FAILURE");
			throw new IllegalArgumentException("Invalid input. No _id");
		}
		return result;
	}

}