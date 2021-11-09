package com.springboot.retailer.app.retailer_points.exceptions;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
	
	public ExceptionResponse() {
		
	}
	
	public ExceptionResponse(String errorMessage, String errorCode, LocalDateTime timestamp, String patch) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.timestamp = timestamp;
		this.patch = patch;
	}
	
	private String errorMessage;
	private String errorCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private String patch;
}
