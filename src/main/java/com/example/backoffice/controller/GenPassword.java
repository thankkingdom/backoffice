package com.example.backoffice.controller;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class GenPassword {

	public static void main(String[] args) {
		System.out.println(new Pbkdf2PasswordEncoder().encode("demo"));
	}

}
