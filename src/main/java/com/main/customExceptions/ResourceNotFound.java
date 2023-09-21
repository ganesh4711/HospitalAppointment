package com.main.customExceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFound extends RuntimeException{
	HttpStatus code;
	public ResourceNotFound(HttpStatus code,String msg) {
		super(msg);
		this.code=code;
	}
	public ResourceNotFound(String msg) {
		super(msg);
		
	}
	
}
