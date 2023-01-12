package com.connection.Practice.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerError {
	
//	long timestamp;
//	HttpStatus status;
//	String errorCode;
//	String errorMessage;
//	long traceId;
//	String errorDeatils;
//	String path;
//	  
	
	 long timestamp;
	    Integer status;
	    String errorCode;
	    String errorMessage;
	    long traceId;
	    String errorDeatils;
	    String path;

}
