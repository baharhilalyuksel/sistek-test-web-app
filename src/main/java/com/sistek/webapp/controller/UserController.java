package com.sistek.webapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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

		User user = (User) model.getAttribute("user");
		if (user == null) {
			user = new User();
			model.addAttribute("user", user);
		}

		return "registration";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

		if (userService.existsByUsername(user.getUsername())) {			
			FieldError fieldError = new FieldError("user", "username", "Geçersiz kullanıcı adı");
			bindingResult.addError(fieldError);
		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "registration";
		}

		userService.saveUser(user);

		return "redirect:/";
	}

	@GetMapping("/newUser")
	public String newUser(Model model) {

		User user = (User) model.getAttribute("user");
		if (user == null) {
			user = new User();
			model.addAttribute("user", user);
		}

		return "newUser";
	}

	@PostMapping("/createNewUser")
	public String createNewUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

		if (userService.existsByUsername(user.getUsername())) {
			FieldError fieldError = new FieldError("user", "username", "Geçersiz kullanıcı adı");
			bindingResult.addError(fieldError);		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "newUser";
		}

		userService.saveUser(user);

		return "redirect:/listUsers";
	}

	@GetMapping("/listUsers")
	public String listUsers(Model model) {
		List<User> users = userService.getUsers();
		model.addAttribute("users", users);
		return "listUsers";
	}

	@GetMapping("/editUser")
	public String editUser(@RequestParam(value = "id") int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "editUser";
	}

	@PostMapping("/updateUser")
	public String updateUser(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

		// username degistirilmisse ve yeni username baska user icin kayitliysa
		if (!userService.getUserById(user.getId()).getUsername().equals(user.getUsername())
				&& userService.existsByUsername(user.getUsername())) {
			FieldError fieldError = new FieldError("user", "username", "Geçersiz kullanıcı adı");
			bindingResult.addError(fieldError);		}

		if (bindingResult.hasErrors()) {
			model.addAttribute("user", user);
			return "editUser";
		}
		userService.updateUser(user);
		return "redirect:/listUsers";
	}

}
