package com.album.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import org.springframework.stereotype.Component;

@Component
final class KeyGeneratorUtils {
	
	private KeyGeneratorUtils() {}
	
	static KeyPair generateRsaKeys() {
		KeyPair keyPair;
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);
			keyPair = keyPairGenerator.generateKeyPair();
			
		}catch(Exception e) {
			throw new IllegalStateException(e);
		}
		return keyPair;
		
	}

}
