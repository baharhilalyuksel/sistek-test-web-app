package com.sistek.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistek.webapp.entity.Barcode;
import com.sistek.webapp.service.BarcodeService;

@Controller
public class BarcodeController {
	
	@Autowired
	BarcodeService barcodeService;
	
	@GetMapping("/listBarcodes")
	public String listBarcodes(Model model) {
		List<Barcode> barcodes = barcodeService.getBarcodes();
		model.addAttribute("barcodes", barcodes);
		model.addAttribute("username", "");
		return "listBarcodes";
	}
	
	@GetMapping("/searchBarcodes")
	public String searchBarcodes(@RequestParam(value = "username") String username, Model model) {
		if(username.equals("")) {
			return "redirect:/listBarcodes";
		}
		List<Barcode> barcodes = barcodeService.getBarcodesByUser(username);
		model.addAttribute("barcodes", barcodes);
		return "listBarcodes";
	}

}
