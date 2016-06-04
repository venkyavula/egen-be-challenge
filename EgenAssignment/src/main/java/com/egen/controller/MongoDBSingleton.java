package com.egen.controller;

import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.mongodb.DB;
import com.mongodb.MongoClient;
/**
 * Database connection to Mongo DB. 
 * @author VENKY
 *
 */
public class MongoDBSingleton {
	private static final Logger logger = Logger.getLogger(MongoDBSingleton.class);
	private static MongoDBSingleton mDbSingleton;
	
	private static MongoClient mongoClient;
    
	private static DB db ;
	
	
	private static final String dbHost = "localhost";
	private static final int dbPort = 27017;
	private static final String dbName = "test";
	private static final String dbUser = "jinna";
	private static final String dbPassword = "jinna";
	
	private MongoDBSingleton(){};
	
	public static MongoDBSingleton getInstance(){
		
		if(mDbSingleton == null){
			logger.info("Connecting to Mongo DB ");
			mDbSingleton = new MongoDBSingleton();
		}
		logger.info("returning connection object");
		return mDbSingleton;
	} 
	
	public DB getTestdb(){
		if(mongoClient == null){
			try {
				mongoClient = new MongoClient(dbHost , dbPort);
			} catch (UnknownHostException e) {
				return null;
			}
		}
		if(db == null)
			db = mongoClient.getDB(dbName);
		if(!db.isAuthenticated()){
			boolean auth = db.authenticate(dbUser, dbPassword.toCharArray());
		}
		return db;
	}
}
