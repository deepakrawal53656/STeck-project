package com.app.response;



public class DataResponse  extends StatusResponse
{
	protected Object data = "";
	
	
	public DataResponse()
	{}

	public DataResponse(int status, String message, Object data){	
		super.status = status;
		this.data = data;
		this.message = message;
	}
	
	
	
	public DataResponse(int status, String message, Object data, Object token) {
		super.status = status;
		this.data = data;
		this.message = message;
		this.token = token;
	}

	public DataResponse(String message, Object data) {
		this.data = data;
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
