package com.album.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
		
		info=@Info(
		title="Album API",
		contact = @Contact(
				name = "hardik", email= "hardiknagpal"
				),
		description="albumAPI"
				))
public class SwaggerConfig {

}
