package com.egen.test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Test;

import com.egen.controller.UserService;
import com.egen.model.Result;
import com.egen.model.User;
import com.google.gson.Gson;

import spark.Spark;
import spark.utils.IOUtils;
/**
 * This class is to test various services in this Component 
 * @author VENKY
 *
 */
public class UserControllerIntegrationTest {
 

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

	@Test
	public void getUserByIdTest() {
		TestResponse res = request("GET", "/getUserById/127");
		Map<String, String> json = res.json();
		assertEquals("127",json.get("id"));
		assertNotEquals("Test", json.get("firstName"));
	}

	@Test
	public void getUserListTest() {
		TestResponse res = request("GET", "/getAllUsers");
		assertNotNull(res);
	}
	@Test
	public void createUserTest() {
		//TestResponse res = request("POST", "/createUser");
		User userObj = new User();
		
		userObj.append("id", "300").append("firstName", "Sachin").append("lastName", "Test Last");
		UserService userService = new UserService();
		// first time when we create user with 300 it shud insert data into db
		Result resFirst = userService.createUser(userObj);
		assertEquals("SUCCESS", resFirst.getStatus());
		// second time when we create user with 300 it shud Throw exception
		boolean ret = false;
		try{
		Result r = userService.createUser(userObj);
		}
		catch(IllegalArgumentException e)
		{
			ret = true;
		}
		assertTrue(ret);
	}
	
	private TestResponse request(String method, String path) {
		try {
			URL url = new URL("http://localhost:4567" + path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setDoOutput(true);
			connection.connect();
			String body = IOUtils.toString(connection.getInputStream());
			return new TestResponse(connection.getResponseCode(), body);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Sending request failed: " + e.getMessage());
			return null;
		}
	}

	private static class TestResponse {

		public final String body;
		public final int status;

		public TestResponse(int status, String body) {
			this.status = status;
			this.body = body;
		}

		public Map<String,String> json() {
			return new Gson().fromJson(body, HashMap.class);
		}
	}
}