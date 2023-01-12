package com.connection.Practice.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EmiException.class)
	public ResponseEntity<ErrorResponce> userExceptionHandler(EmiException ue, WebRequest wb, Exception e) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.BAD_REQUEST.value())
				.errorMessage(HttpStatus.BAD_REQUEST.name()).path(wb.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ue.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param nValid :its argument of validate.
	 * @return : its return response entity of Map.
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> error = ex.getBindingResult().getFieldErrors();
		EmiErrorResponce emiVException = new EmiErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(),
				error.get(0).getDefaultMessage());
		return new ResponseEntity<>(emiVException, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(DateException.class)
	public ResponseEntity<EmiErrorResponce> dateReponseGenerate(DateException excp) {
		EmiErrorResponce resp = new EmiErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(), excp.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		EmiErrorResponce resp = new EmiErrorResponce(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value())
				.errorMessage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.SERVICE_UNAVAILABLE.value())
				.errorMessage(HttpStatus.SERVICE_UNAVAILABLE.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.NOT_FOUND.value())
				.errorMessage(HttpStatus.NOT_FOUND.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.NOT_ACCEPTABLE.value())
				.errorMessage(HttpStatus.NOT_ACCEPTABLE.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.METHOD_NOT_ALLOWED.value())
				.errorMessage(HttpStatus.METHOD_NOT_ALLOWED.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.value())
				.errorMessage(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.HTTP_VERSION_NOT_SUPPORTED);
	}

	@Override
	protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.NOT_IMPLEMENTED.value())
				.errorMessage(HttpStatus.NOT_IMPLEMENTED.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1)
				.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value())
				.errorMessage(HttpStatus.NON_AUTHORITATIVE_INFORMATION.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NON_AUTHORITATIVE_INFORMATION);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.NOT_IMPLEMENTED.value())
				.errorMessage(HttpStatus.NOT_IMPLEMENTED.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.NOT_FOUND.value())
				.errorMessage(HttpStatus.NOT_FOUND.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(ex.getMessage()).build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		ex.printStackTrace(printWriter);
		String stackTrace = stringWriter.toString();
		ErrorResponce error = ErrorResponce.builder().errorCode(1).status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).path(request.getDescription(false))
				.timestamp(Timestamp.valueOf(LocalDateTime.now())).traceID(Instant.now().toEpochMilli())
				.errorDetails(stackTrace).build();
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
