package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemDto implements Serializable {
	private static final long serialVersionUID = -2076961574919840235L;
	private Long itemCod;
	private String itemName;
}
