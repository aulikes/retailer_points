package com.springboot.retailer.app.retailer_points.exceptions;

public class ResourceAlreadyExists extends RuntimeException {
	private static final long serialVersionUID = 2906506209885014655L;

	public ResourceAlreadyExists(String message) {
        super(message);
    }
}
