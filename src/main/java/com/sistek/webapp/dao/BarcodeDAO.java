package com.sistek.webapp.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.sistek.webapp.entity.Barcode;
import com.sistek.webapp.entity.User;

public interface BarcodeDAO extends Repository<Barcode, Long> {
	
	public List<Barcode> findAll();

	public void save(Barcode barcode);

	public List<Barcode> getBarcodesByUser(User user);


}
