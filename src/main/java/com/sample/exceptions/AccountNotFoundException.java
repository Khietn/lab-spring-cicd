package com.sample.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class AccountNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public AccountNotFoundException(String cause) {
		super(String.format("No such account with $s", cause));
	}
}
