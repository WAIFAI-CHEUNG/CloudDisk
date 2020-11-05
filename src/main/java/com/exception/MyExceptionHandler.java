package com.exception;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


public class MyExceptionHandler implements HandlerExceptionResolver{

	@Override
	@ExceptionHandler
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		/*
		 * 错误信息的处理 
		 * 1.下面的配置信息即指定了错误信息存储的变量名
		 *  2.将变量名放到attribute里面去
		 */
		HashMap<String,Object> model = new HashMap<String,Object>();
		model.put("ex", ex);
		
		/* 根据异常不同，做不同处理 */
		if(ex instanceof MyException) {
			ModelAndView modelAndView = new ModelAndView("comment/my_error",model);
			return modelAndView;
		}else {
			ModelAndView modelAndView = new ModelAndView("comment/default_error",model);
			return modelAndView;
		}
	}

}
