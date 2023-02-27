package com.grupo1.gestoreventos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptaPassword {

	public static void main(String[] args){
		var password = "jrubio";
		System.out.println("password: "+password);
		System.out.println("password encriptado: "+encriptarPassword(password));
	}
	
	public static String encriptarPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
}