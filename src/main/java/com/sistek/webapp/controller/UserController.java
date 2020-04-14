package com.sistek.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistek.webapp.entity.User;
import com.sistek.webapp.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/registration")
	public String registration(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);

		return "registration";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user) {
		
		userService.saveUser(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/newUser")
	public String newUser(Model model) {
		
		User user = new User();
		model.addAttribute("user", user);

		return "newUser";
	}
	
	@PostMapping("/createNewUser")
	public String createNewUser(@ModelAttribute User user) {
		
		userService.saveUser(user);
		
		return "newUser";
	}
	
	@GetMapping("/listUsers")
	public String listUsers(Model model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "listUsers";
	}
	
	@GetMapping("/editUser")
	public String editUser(@RequestParam(value = "id") int id, Model model) {
//		System.out.println("User id = " + id);
//		int userId = Integer.parseInt(id);
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute User user) {
		userService.saveUser(user);
		return "redirect:/listUsers";
	}

}
