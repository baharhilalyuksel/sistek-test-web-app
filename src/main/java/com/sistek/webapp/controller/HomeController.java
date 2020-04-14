package com.sistek.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistek.webapp.service.BarcodeService;
import com.sistek.webapp.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BarcodeService barcodeService;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("userCount", userService.getUsers().size());
		model.addAttribute("barcodeCount", barcodeService.getBarcodes().size());
		return "home";
	}

}
