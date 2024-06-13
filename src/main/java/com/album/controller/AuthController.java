package com.album.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody; 
import com.album.Dto.AccountDto;
import com.album.Dto.Token;
import com.album.Dto.UserLogin;
import com.album.model.Account;
import com.album.service.AccountService;
import com.album.service.TokenService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenservice;
	@Autowired
	private AccountService accountService;

	@PostMapping(value="/token",produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	@ApiResponse(responseCode = "400",description = "failed to generate token" )
	@ApiResponse(responseCode = "201",description = "generate token" )
	@Operation(summary="generate Token")
	public ResponseEntity<Token> token(@Valid @RequestBody UserLogin userLogin) throws AuthenticationException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
			return ResponseEntity.ok(new Token(tokenservice.generateToken(authentication)));
		} catch (Exception e) {
				e.printStackTrace();
			return new ResponseEntity<>(new Token(null), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value="/user/add" ,consumes ="application/json",produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponse(responseCode = "400",description = "failed to add user" )
	@ApiResponse(responseCode = "201",description = "user added successfully" )
	@Operation(summary="add new user")
	public ResponseEntity<String> addUser(@Valid @RequestBody AccountDto accountDto){
		Account account = new Account();
		System.out.println("-----"+accountDto);
		try {
			System.out.println("-----"+accountDto);
			account.setEmail(accountDto.getEmail());
			account.setPassword(accountDto.getPassword());
			account.setAuthority("user");
			accountService.save(account);
			System.out.println("account"+account);
			return ResponseEntity.ok("successfully added");
		}catch(Exception e) {
			e.printStackTrace();
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail to add");
		}
	}
	@GetMapping(value="/userList" ,produces = "application/json")
	@ApiResponse(responseCode = "400",description = "bad request" )
	@ApiResponse(responseCode = "201",description = "complete list of user" )
	@Operation(summary="add new user")
	public List<Account> User(){
		return  accountService.findAll();

	}
	@PostMapping(value="/user/add/new")
	public Account addUserTest(@RequestBody AccountDto accountDto){
		Account account = new Account();
		System.out.println("-----"+accountDto);
			System.out.println("-----"+accountDto);
			account.setEmail(accountDto.getEmail());
			account.setPassword(accountDto.getPassword());
			account.setAuthority("user");
			accountService.save(account);
			System.out.println("account"+account);
			return account;
	}
}
