package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ClientPointsDto implements Serializable {
	private static final long serialVersionUID = -5275646753504514788L;
	
    private Long idClient;
    private String firstName;
    private String lastName;
	private Long totalPoints;
	private List<PointMonthDto> pointsMonth;
	
	public void sumPoints(Long sum) {
		totalPoints = totalPoints == null ? sum : sum + totalPoints;
	}
	
}
