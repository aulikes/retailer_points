package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PurchaseOrderDto implements Serializable {
	private static final long serialVersionUID = 7979019810304206181L;

	@JsonProperty(access = Access.READ_ONLY)
	private Long idPurchase;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Schema(example = "2021-08-92")
	private String date;

	@JsonProperty(access = Access.READ_ONLY)
	private Long points;

	@NotNull
	@Schema(required = true, example = "10")
	private Long idClient;

	@NotNull
	@Schema(required = true)
	private List<ItemOrderDto> items;
}
