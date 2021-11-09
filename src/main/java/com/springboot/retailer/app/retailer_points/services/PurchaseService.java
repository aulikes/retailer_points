package com.springboot.retailer.app.retailer_points.services;

import java.util.List;

import com.springboot.retailer.app.retailer_points.dto.ClientPointsDto;
import com.springboot.retailer.app.retailer_points.entities.Purchase;

public interface PurchaseService {

	Purchase save(Purchase entity);
	
	List<ClientPointsDto> getClientsPoints();
	
	ClientPointsDto getClientPoints(Long idClient);
}
