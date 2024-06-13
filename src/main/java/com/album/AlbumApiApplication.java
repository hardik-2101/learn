package com.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
// for authorization in swagger ui 
@SecurityScheme(name="Album API",scheme="bearer",type =SecuritySchemeType.HTTP,in = SecuritySchemeIn.HEADER)
public class AlbumApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumApiApplication.class, args);
	}

}
