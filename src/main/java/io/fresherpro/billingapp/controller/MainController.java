package io.fresherpro.billingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/admin")
	public String home()
	{
		return "index";
	}
	@GetMapping("/user")
	public String user()
	{
		return "billing";
	}
}
