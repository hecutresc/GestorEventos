package com.grupo1.gestoreventos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptaPassword {

	public static void main(String[] args) {
		var password = "admin123";
		System.out.println("password: " + password);
		System.out.println("password encriptado:" + encriptarPassword(password));
		password = "123456";
		System.out.println("password: " + password);
		System.out.println("password encriptado:" + encriptarPassword(password));
	}

	public static String encriptarPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}
