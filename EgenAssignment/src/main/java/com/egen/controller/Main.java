package com.egen.controller;

import org.apache.log4j.Logger;
/**
 * Driver class for this application
 * @author VENKY
 *
 */
public class Main {
	private static final Logger logger = Logger.getLogger(UserService.class);
	public static void main(String[] args) {
		logger.info("Server has been started and its listening on 4567 port ");
		new UserController(new UserService());
		logger.info("Application has been closed/terminated ");	
	}
}