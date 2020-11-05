package com.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LonginIntercept implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 获取请求的RUI:去除http:localhost:8080这部分剩下的
		String requestURI = request.getRequestURI();
		if (requestURI.indexOf("/CloudDiskBy_zwh/") >= 0) {
			if (request.getSession().getAttribute("loginStatus") == null
					|| request.getSession().getAttribute("loginStatus") == "") {
				// 用户没有登陆,将请求转发到登陆
				request.getRequestDispatcher("/unLogin.jsp").forward(request, response);
				return false;
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// HandlerInterceptor.super.postHandle(request, response, handler,
		// modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
