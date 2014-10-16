package com.adorsys.projet.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DbCollectionUtil {
	public static DB getdb() {
		MongoClient mongoClient = null;
		try {
		mongoClient = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
		e.printStackTrace();
		}
		DB db = mongoClient.getDB( "projetagricole" );
		//DBCollection dbCollection = db.getCollection("testcollection");
		return db;
		}
}
