package com.sistek.webapp.service;

import java.util.List;

import com.sistek.webapp.entity.User;

public interface UserService {
	
	public void saveUser(User user);

	public List<User> getUsers();

	public User getUserById(int id);

}
