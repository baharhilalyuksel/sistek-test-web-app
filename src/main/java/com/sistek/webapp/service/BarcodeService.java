package com.sistek.webapp.service;

import java.util.List;

import com.sistek.webapp.entity.Barcode;

public interface BarcodeService {
	
	public List<Barcode> getBarcodes();

	public void saveBarcode(String barcode, String username);

	public List<Barcode> getBarcodesByUser(String username);

}
