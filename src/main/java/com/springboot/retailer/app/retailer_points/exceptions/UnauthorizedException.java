package com.springboot.retailer.app.retailer_points.exceptions;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = 5179661497943856624L;

	public UnauthorizedException(String message) {
		super(message);
	}

}
