package com.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class MyBaseController {
	@ExceptionHandler
	public String exceptionHandle(HttpServletRequest request,Exception ex) {
		request.setAttribute("allErrors", ex);
		if(ex instanceof MyException) {
			return "comment/my_error";
		}else {
			return "comment/default_error";
		}
	}

}
