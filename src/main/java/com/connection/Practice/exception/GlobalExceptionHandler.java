package com.connection.Practice.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {



	@ResponseStatus
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> inavlidArgument(MethodArgumentNotValidException ex, WebRequest req,
			BindingResult result) {

		ValidationError ve = new ValidationError(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				ex.getBindingResult().getFieldError().getDefaultMessage(), req.getDescription(false));
		return new ResponseEntity<>(ve, HttpStatus.NOT_FOUND);

	}

	@ResponseStatus
	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ServerError> maxInstallmentPaid(InternalServerError ex, WebRequest req,
			BindingResult result) {

		ServerError se = new ServerError(Instant.now().toEpochMilli(), HttpStatus.NOT_FOUND.value(), "1", ex.getMessage(),
				Instant.now().getEpochSecond(), "Your Inputs are not valid", req.getDescription(false));
		return new ResponseEntity<>(se, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServerError> serverErrorHandling(Exception ex, HttpServletRequest req,
			HttpServletResponse res, WebRequest re) {

		ServerError se = new ServerError(Instant.now().toEpochMilli(), HttpStatus.NOT_FOUND.value(), "1", ex.getMessage(),
				Instant.now().getEpochSecond(), "Your Inputs are not valid", re.getDescription(false));
		return new ResponseEntity<>(se, HttpStatus.NOT_FOUND);

	}

}