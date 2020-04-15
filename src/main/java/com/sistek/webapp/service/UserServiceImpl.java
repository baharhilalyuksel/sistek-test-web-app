package com.sistek.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistek.webapp.dao.AuthorityDAO;
import com.sistek.webapp.dao.UserDAO;
import com.sistek.webapp.entity.Authority;
import com.sistek.webapp.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	AuthorityDAO authorityDao;

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
	public void updateUser(User user) {
		Authority authority = getAuthorityByUserId(user.getId());
		
		user.setEnabled(true);
		String password = user.getPassword();
		user.setPassword("{noop}" + password);
		userDao.save(user);
		
		authority.setUsername(user.getUsername());
		authority.setAuthority("ROLE_USER");
		userDao.save(authority);

	}
	
	private Authority getAuthorityByUserId(int id) {
		User user = getUserById(id);
		return authorityDao.getAuthorityByUsername(user.getUsername());
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

	@Override
	public Boolean existsByUsername(String username) {
		return userDao.existsByUsername(username);
	}

}
