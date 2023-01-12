package com.connection.Practice.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
	

//	Integer status;
//    LinkedHashMap<String, String> errorMessage;
//    String path;
	
	 Integer status;
     String errorMessage;
     String path;
}
