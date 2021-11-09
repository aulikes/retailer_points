package com.springboot.retailer.app.retailer_points.exceptions;

public class BadRequesException extends RuntimeException {

	private static final long serialVersionUID = -2911526766275658495L;

	public BadRequesException(String message) {
		super(message);
	}
}
