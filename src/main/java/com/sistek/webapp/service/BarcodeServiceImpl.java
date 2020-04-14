package com.sistek.webapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistek.webapp.dao.BarcodeDAO;
import com.sistek.webapp.dao.UserDAO;
import com.sistek.webapp.entity.Barcode;
import com.sistek.webapp.entity.User;

@Service
public class BarcodeServiceImpl implements BarcodeService {
	
	@Autowired
	BarcodeDAO barcodeDao;
	
	@Autowired
	UserDAO userDao;

	@Override
	@Transactional
	public List<Barcode> getBarcodes() {
		return barcodeDao.findAll();
	}

	@Override
	@Transactional
	public void saveBarcode(String barcode, String username) {
		Barcode barcodeObject = new Barcode();
		barcodeObject.setBarcode(barcode);
		User user = userDao.getUserByUsername(username);
		barcodeObject.setUser(user);
		barcodeDao.save(barcodeObject);
	}

	@Override
	@Transactional
	public List<Barcode> getBarcodesByUser(String username) {
		User user = userDao.getUserByUsername(username);
		if (user == null) {
			return new ArrayList<Barcode>();
		}
		return barcodeDao.getBarcodesByUser(user);
	}

}
