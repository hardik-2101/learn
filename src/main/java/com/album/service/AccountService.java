package com.album.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.album.model.Account;
import com.album.repository.UserRepository;

@Service
public class AccountService implements UserDetailsService{

		@Autowired
		UserRepository userRepository;
		@Autowired 
		private PasswordEncoder passwordEncoder;
		
		public Account save(Account account) {
			account.setPassword(passwordEncoder.encode(account.getPassword()));
			if(account.getAuthority() == null) {
				account.setAuthority("USER");
			}
			return userRepository.save(account);
		}

		@Override
		public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email)
				throws UsernameNotFoundException {
			Optional<Account> optionalUser = userRepository.findByEmail(email);
			if(!optionalUser.isPresent()) {
				throw new UsernameNotFoundException("No account exist");
			}
			Account account = optionalUser.get();
			List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
			list.add(new SimpleGrantedAuthority("admin"));
			return new User(account.getEmail(),account.getPassword(),list);
		}

		public List<Account> findAll() {
			List<Account> user = userRepository.findAll();
			return user;
		}
}
