package com.album.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.album.model.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByEmail(String email);
}
