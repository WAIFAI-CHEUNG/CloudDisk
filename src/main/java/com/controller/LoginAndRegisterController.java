package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/LoginAndRegister")
public class LoginAndRegisterController {
	@RequestMapping("/login.action")
	public String LoginSuccess() {
		return "redirect:/index.jsp";
	}
	@RequestMapping("/register.action")
	public String registerSuccess() {
		return "redirect:/registerSuccess.jsp";
	}
}
