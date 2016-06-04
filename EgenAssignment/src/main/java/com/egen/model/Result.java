package com.egen.model;
/**
 * This class is to get the status of each method from Service class to controller.
 * @author VENKY
 *
 */
public class Result {

	private String message;
	String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result(String message, String... args) {
		this.message = String.format(message, args);
	}

	public Result(Exception e) {
		this.message = e.getMessage();
		this.status= "FAILURE";
	}

	public Result() {
	}

	public String getMessage() {
		return this.message;
	}
}