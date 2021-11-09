package com.springboot.retailer.app.retailer_points.utils;

public class PurchasePoint {

	public static long calculatePoints(double value) {
		long points = 0;
		double newValue = 0;
		if (value > 100) {
			newValue = value - 100;
			points += (Math.floor(newValue) * 2);
		}
		if (value > 50) {
			points += Math.floor(value - newValue - 50);
		}
		return points;
	}
}
