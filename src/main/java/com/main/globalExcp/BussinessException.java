package com.main.globalExcp;

import org.springframework.http.HttpStatus;

public class BussinessException extends RuntimeException{
	HttpStatus code;
	public BussinessException(HttpStatus code,String msg) {
		super(msg);
		this.code=code;
	}
	public BussinessException(String msg) {
		super(msg);
		
	}
	
}
