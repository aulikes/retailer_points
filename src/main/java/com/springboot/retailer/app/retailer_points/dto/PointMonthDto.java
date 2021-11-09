package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class PointMonthDto implements Serializable {
	private static final long serialVersionUID = -365554083602372792L;
	
	private String month;
	private Long points;
}
