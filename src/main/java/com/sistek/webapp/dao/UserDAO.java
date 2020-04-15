package com.sistek.webapp.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sistek.webapp.entity.Authority;
import com.sistek.webapp.entity.User;

public interface UserDAO extends Repository<User, Long>{
	
	public void save(User user);

	public List<User> findAll();

	public void save(Authority authority);

	public User getUserByUsername(String username);

	public User findUserById(int id);
	
	public Boolean existsByUsername(String username);

}
