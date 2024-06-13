package com.album.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.album.model.Account;
import com.album.service.AccountService;

@Component
public class SeedData implements CommandLineRunner {
		
	@Autowired
	AccountService accountService;
	
	@Override
	public void run(String... args) throws Exception {
		Account userDetails1 = new Account();
		userDetails1.setEmail("adim@gmail.com");
		userDetails1.setPassword("pass234");
		accountService.save(userDetails1);
		
	}

}
