package com.sistek.webapp.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistek.webapp.entity.User;
import com.sistek.webapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public List<User> getUsers(){
		return userService.getUsers();
	}
	
	@GetMapping("/login")
	public String login(HttpServletResponse response) {
		return "OK";
	}

}
