package com.sistek.webapp.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistek.webapp.entity.Barcode;
import com.sistek.webapp.service.BarcodeService;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeRestController {
	
	@Autowired
	BarcodeService barcodeService;
	
	@GetMapping
	public List<Barcode> getBarcodesByUser() {
		
		List<Barcode> barcodes = barcodeService.getBarcodesByUser(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return barcodes;
	}
	
	@PostMapping("/{barcode}")
	public String addBarcode(@PathVariable String barcode) {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		barcodeService.saveBarcode(barcode, SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "Success!";
	}
	
	@GetMapping("/{barcode}")
	public String getBarcode(@PathVariable String barcode) {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return "Success!";
	}

}
