package com.di.bookstore.responses;

public class Response {
	
	private int status;
	private String message;
	private Object data;
	
	public Response(String message,int status) {
		this.message = message;
		this.status = status;
	}

	public Response(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
