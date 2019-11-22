package com.app.customexception;

public class AlreadyExistException extends Exception{

	public AlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
