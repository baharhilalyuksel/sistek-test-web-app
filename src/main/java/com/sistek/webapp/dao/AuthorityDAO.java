package com.sistek.webapp.dao;

import org.springframework.data.repository.Repository;

import com.sistek.webapp.entity.Authority;

public interface AuthorityDAO extends Repository<Authority, Long>{

	public Authority getAuthorityByUsername(String username);
	
}
