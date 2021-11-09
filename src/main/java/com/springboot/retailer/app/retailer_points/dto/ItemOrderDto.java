package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ItemOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Schema(description = "the itemCod identifier", required = true, example = "100")
	private Long itemCod;

	@Schema(example = "Disco")
	@Size(min = 1, max = 20, message = "Must have a itemName")
	@NotBlank(message = "The itemName is required.")
	private String itemName;

	@Schema(description = "the item value", required = true, example = "500")
	private double itemValue;

	@Schema(description = "the item amount", required = true, example = "2")
	private int cant;
}
