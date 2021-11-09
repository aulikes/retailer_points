package com.springboot.retailer.app.retailer_points.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.retailer.app.retailer_points.converters.PurchaseConverter;
import com.springboot.retailer.app.retailer_points.dto.ClientPointsDto;
import com.springboot.retailer.app.retailer_points.dto.PurchaseOrderDto;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.exceptions.ExceptionResponse;
import com.springboot.retailer.app.retailer_points.services.PurchaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Validated
@RequestMapping("/api/purchases")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseConverter purchaseConverter;

    @Operation(description = "Save Purchase", summary = "Save Purchase-Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseOrderDto> savePurchase(@Valid @RequestBody PurchaseOrderDto purchaseOrder) {
    	Purchase purchase = purchaseService.save(purchaseConverter.toEntity(purchaseOrder));
    	return new ResponseEntity<PurchaseOrderDto>(purchaseConverter.toDto(purchase), HttpStatus.CREATED);
    }
    
    @Operation(description = "Get Point-Purchase Client", summary = "Get Point-Purchase Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping(value = "/points/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClientPointsDto> listPointClients(){
    	return purchaseService.getClientsPoints();
	}

	
    @GetMapping(value = "/points/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content(schema = @Schema(implementation = ExceptionResponse.class)))
    })
    public ClientPointsDto getPointClient(@PathVariable Long idClient) {
    	return purchaseService.getClientPoints(idClient);
    }
}
