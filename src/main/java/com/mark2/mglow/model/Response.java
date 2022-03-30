package com.mark2.mglow.model;

public class Response {
	int status;
	String message;
	
	public Response(String message,int status) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [status=" + status + ", message=" + message + "]";
	}
}
