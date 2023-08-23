package com.mytimeaway.exception;

public class ApplicationNotFoundException extends RuntimeException {

	public ApplicationNotFoundException(String message) {
		super(message);
	}
}
