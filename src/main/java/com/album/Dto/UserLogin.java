package com.album.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLogin {
	@Email
	@Schema(description = "enter valid email please",example = "user@user.com",requiredMode = RequiredMode.REQUIRED)
	private String email;
	@Schema(description = "password",example = "password",requiredMode = RequiredMode.REQUIRED)
	private String password;

}
