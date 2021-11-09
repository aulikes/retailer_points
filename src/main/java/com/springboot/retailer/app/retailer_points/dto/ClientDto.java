package com.springboot.retailer.app.retailer_points.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ClientDto implements Serializable { 
	private static final long serialVersionUID = 5510767440238254707L;

	@Schema(description = "the client identifier", required = true, example = "10")
    private Long idClient;

	@Schema(example = "JHON")
	@Size(min = 1, max = 20, message = "Must have a firstname")
	@NotBlank(message = "The firstname is required.")
    private String firstName;

	@Schema(example = "SMITH")
	@Size(min = 1, max = 20, message = "Must have a lastname")
	@NotBlank(message = "The lastName is required.")
    private String lastName;
}
