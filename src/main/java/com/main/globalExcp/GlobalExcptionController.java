package com.main.globalExcp;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public ResponseEntity<ErrorResponse> handle(){
		var status=HttpStatus.INTERNAL_SERVER_ERROR;

		return new ResponseEntity<>(
				new ErrorResponse("Meathod Aruguments MisMatched"),status);
	}
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(MismatchedInputException.class)
	public  ResponseEntity<ErrorResponse> handleMissMatchedArgu(){
		return new ResponseEntity<>(
				new ErrorResponse("MisMatched Input"),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}
}
