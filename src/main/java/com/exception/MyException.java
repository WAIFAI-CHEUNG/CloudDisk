package com.exception;

/*自定义异常*/
public class MyException extends Exception {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MyException(String message) {
		super();
		this.message = message;
	}
	

}
