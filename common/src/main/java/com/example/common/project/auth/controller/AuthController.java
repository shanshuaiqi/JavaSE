package com.example.common.project.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类功能描述
 *
 * @author ssq
 * @date 2019-10-31 上午 10:53
 */
@Controller
public class AuthController {

	@RequestMapping("/test")
	public Object test() {
		return "hello world";
	}

	@RequestMapping("/index")
	public String toIndex(Model model) {
		model.addAttribute("name", "lisi");
		return "index";
	}
}