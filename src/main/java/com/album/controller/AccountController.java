package com.album.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class AccountController {
	
	@GetMapping("/")
	public String demo() {
		return "this is demo api";
	}
	
	@GetMapping("/test")
	@Tag(name="Test",description="testing")
	@SecurityRequirement(name="Album API")
	public String test() {
		return "test";
	}
}
