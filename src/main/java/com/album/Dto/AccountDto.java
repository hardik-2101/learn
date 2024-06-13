package com.album.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public class AccountDto {
	@Email
	@Schema(description = "enter valid email", example = "user@gmail.com",requiredMode = RequiredMode.REQUIRED)
	private String email;
	@Size(min=6, max=20)
	@Schema(description = "password ", example = "password",requiredMode = RequiredMode.REQUIRED,minLength = 6,maxLength = 20)
	private String password;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AccountDto [email=" + email + ", password=" + password + "]";
	}
	
	

}
