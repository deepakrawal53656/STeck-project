package com.app.response;


public class StatusResponse implements RestResponse{
	
	protected int status;
	
	protected String message;
	
	protected Object token;
	
	public StatusResponse(int status) {
		this.status = status;
	}

	public StatusResponse() {
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

	public Object getToken() {
		return token;
	}

	public void setToken(Object token) {
		this.token = token;
	}



}
