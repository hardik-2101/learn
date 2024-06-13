package com.album.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSecurityContextJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class config {
	private  RSAKey rsaKeys ;
	
	@Bean
	public JWKSource<SecurityContext> jwkSource(){
		rsaKeys = Jwks.generateRsa();
		JWKSet jwkSet = new JWKSet(rsaKeys);
		
		return (JWKSelector , SecurityContext) -> JWKSelector.select(jwkSet);
	}

	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public InMemoryUserDetailsManager user() {
//		return new InMemoryUserDetailsManager(
//				User.withUsername("hardik")
//					.password("testing")
//					.authorities("read")
//					.build());
//	}
	
	@Bean
	public AuthenticationManager authManager(UserDetailsService userDetailsService) {
		var authProvider = new DaoAuthenticationProvider();
		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(authProvider);
		
	}
	@Bean
	JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwks) {
//		JWK jwk= new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
//		JWKSource<SecurityContext> jwk1 = new ImmutableJWKSet<>(new JWKSet(jwk));
		return new NimbusJwtEncoder(jwks);
	}
	@Bean
	JwtDecoder jwtDecoder() throws JOSEException {
		return NimbusJwtDecoder.withPublicKey(rsaKeys.toRSAPublicKey()).build();
	}
	@Bean
	public SecurityFilterChain securityChain(HttpSecurity http) throws Exception  {
		http
			.headers().frameOptions().sameOrigin()
			.and()
			.authorizeHttpRequests()
			.requestMatchers("/**").permitAll()
			.requestMatchers("/swagger-ui/**").permitAll()
			.requestMatchers("/").permitAll()
			.requestMatchers("/auth/token").permitAll()
			.requestMatchers("/auth/user/add").permitAll()
			.requestMatchers("/v3/api-docs/**").permitAll()
			.requestMatchers("/test").authenticated()
			.and()
			.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			http.csrf().disable();
			http.headers().frameOptions().disable();
					
			
		return http.build();
		
	}

}
