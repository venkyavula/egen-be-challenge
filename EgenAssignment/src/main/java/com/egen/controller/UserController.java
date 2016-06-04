package com.egen.controller;

import static com.egen.controller.JsonUtil.json;
import static com.egen.controller.JsonUtil.toJson;
import static spark.Spark.after;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.egen.model.Result;
import com.egen.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
/**
 * This is the controller class which is having all the services that this application offering. 
 * They are 
 * 1. getUserById  
 * 2. getAllUsers
 * 3. updateUser
 * 4. createUser
 * 
 * @author VENKY
 *
 */
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);
	public UserController(final UserService userService) {

		/**
		 * method will return the user object in JSON format for given USERID
		 * 
		 */
		get("/getUserById/:id", (req, res) -> {
			String id = req.params(":id");
			logger.info("There was a request for url /getUserById/"+id);
			BasicDBObject user = userService.getUser(id);
			if (user != null) {
				return user;
			}
			res.status(404);
			logger.error("No user with id "+id+"found");
			return new Result("No user with id '%s' found", id);
			
		} , json());
		
		/**
		 * This method will return all the users that are presented in the database. 
		 */
		
		get("/getAllUsers", (req, res) -> {
			logger.info("There was a request for url /getAllUsers/");
			ArrayList<BasicDBObject> userList = userService.getUsers();
			if (userList != null) {
				return userList;
			}
			res.status(404);
			return new Result("There is some error occured ");
		} , json());
		/**
		 * This method will take JSON as input and it will create an user in the data base.
		 */
		post("/createUser", (request, response) -> {
			logger.info("There was a request for url /createUser/");
			try {
				ObjectMapper mapper = new ObjectMapper();
				User createUser = mapper.readValue(request.body(), User.class);
				Result result = userService.createUser(createUser);
				if(result.getStatus().equals("SUCCESS")){
				response.status(200);
				response.type("application/json");
				response.body(toJson(result));
				}
				else
				{
					response.status(404);
					response.type("application/json");
					response.body(toJson(result));
				}

			} catch (JsonParseException jpe) {
				logger.error(jpe.getMessage());
				response.status(404);
				return response;
			} catch (Exception e) {
				logger.error(e.getMessage());
				response.status(404);
			}
			return response;
		} );
		
		/**
		 * This method will update the given user object if it is already present in the data base. 
		 * otherwise it will return 404 status to the user who is requesting
		 */
		put("/updateUser", (request, response) -> {
			try {
				ObjectMapper mapper = new ObjectMapper();
				User updateUser = mapper.readValue(request.body(), User.class);
				Result rObj = userService.updateUser(updateUser);
				if(rObj.getStatus().equals("SUCCESS")){
				response.status(200);
				response.type("application/json");
				}
				

			} catch (JsonParseException jpe) {
				logger.error(jpe.getMessage());
				response.status(404);
				return response;
			} catch (Exception e) {
				logger.error(e.getMessage());
				response.status(404);
			}
			return response;
		} );

		after((req, res) -> {
			res.type("application/json");
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			res.status(404);
			res.body(toJson(new Result(e)));
		});
	}
}