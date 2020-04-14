package com.sistek.webapp.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestDbController {
	
	@RequestMapping("/testdb")
	public String testdb() {
		
		String user = "sistektest";
		String pass = "sistektest";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/sistektest?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";
		
		try {
			
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, pass);
			
			myConnection.close();
			
			return "SUCCESS";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "connection failed";
		}
	}

}
