package com.main.globalExcp;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

@ControllerAdvice
public class GlobalExcptionController {

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElement(Exception e) {
		HttpStatus status=HttpStatus.NOT_FOUND;
		 StringWriter stringWriter = new StringWriter();
		var printWriter=new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		String stack=stringWriter.toString();
		return new ResponseEntity<>(new ErrorResponse(status,"there is no element with id",stack),status);
	}

	@ExceptionHandler(NumberFormatException.class)
	public ResponseEntity<ErrorResponse> handleNumFormat(Exception e){
		 HttpStatus status = HttpStatus.BAD_REQUEST; // 404

		    // converting the stack trace to String
		    StringWriter stringWriter = new StringWriter();
		    PrintWriter printWriter = new PrintWriter(stringWriter);
		    e.printStackTrace(printWriter);
		    String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(
				 new ErrorResponse(status,"Enter proper valid numbers only",stackTrace),status);
	}
	@ExceptionHandler(BussinessException.class)
	public ResponseEntity<ErrorResponse> handleBussinessExcp(Exception e){
		var status=HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<>(
				 new ErrorResponse(status,e.getMessage()),status);	
	}
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handle(Exception e){
		return new ResponseEntity<String>("Arguments not matched ",HttpStatus.BAD_REQUEST);
		
	}
}
