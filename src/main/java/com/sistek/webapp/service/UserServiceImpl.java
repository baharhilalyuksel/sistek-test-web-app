package com.sistek.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistek.webapp.dao.UserDAO;
import com.sistek.webapp.entity.Authority;
import com.sistek.webapp.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDao;

	@Override
	@Transactional
	public void saveUser(User user) {
		user.setEnabled(true);
		String password = user.getPassword();
		user.setPassword("{noop}" + password);
		userDao.save(user);
		
		Authority authority = new Authority();
		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_USER");
		userDao.save(authority);

	}

	@Override
	@Transactional
	public List<User> getUsers() {
		return userDao.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userDao.findUserById(id);
	}

}
