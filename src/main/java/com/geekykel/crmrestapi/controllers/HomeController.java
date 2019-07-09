package com.geekykel.crmrestapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//	@RequestMapping("/")
//	public String showPagee() {
//		return "main-menu";
//	}
	
	@RequestMapping("/main")
	public String showPage() {
		return "main-menu";
	}

}
