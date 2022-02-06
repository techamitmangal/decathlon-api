package com.decathlon.api.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	private final Random RANDOM = new SecureRandom();
	private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	public String generateUserId(int length) {
		return generateRandomString(length);
	}
	
	private String generateRandomString (int length) {
		StringBuilder returnValue = new StringBuilder(length);
		
		for (int i=0; i<length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		
		return new String(returnValue);
	}
	
	public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                              
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
