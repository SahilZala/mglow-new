package com.mark2.mglow.model;

public class ErrorResponse {
	String message;
	
	public ErrorResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return message;
	}
	
}
