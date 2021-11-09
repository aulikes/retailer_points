package com.springboot.retailer.app.retailer_points.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

	@Autowired
	MessageSource message;

	private final String defaultMessage = "Internal error";

	private final LocalDateTime now = LocalDateTime.now();

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<ExceptionResponse> handleUnknowExceptionConflict(Exception ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(defaultMessage, HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				now, request.getServletPath());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ExceptionResponse> handleargumentNotValidException(Exception ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse("your arguments must comply with the established rules",
				HttpStatus.BAD_REQUEST.toString(), now, request.getServletPath());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}

	// --------------------------------------------------------------------------------------------------

	@ExceptionHandler(value = { UnauthorizedException.class })
	public ResponseEntity<ExceptionResponse> handleUnauthorizedException(UnauthorizedException ex,
			HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString(), now,
				request.getServletPath());
//	        LOGGER.error("Invalid Input Exception: ",ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = { ResourceAlreadyExists.class })
	public ResponseEntity<ExceptionResponse> handleResourceAlreadyExists(Exception ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.CONFLICT.toString(), now,
				request.getServletPath());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = { ResourceNotFoundException.class })
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(Exception ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), now,
				request.getServletPath());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { BadRequesException.class })
	public ResponseEntity<ExceptionResponse> handleBadRequesException(Exception ex, HttpServletRequest request) {
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString(), now,
				request.getServletPath());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
