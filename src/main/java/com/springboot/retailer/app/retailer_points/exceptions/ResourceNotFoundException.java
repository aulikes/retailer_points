package com.springboot.retailer.app.retailer_points.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3897112605183069146L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

}
