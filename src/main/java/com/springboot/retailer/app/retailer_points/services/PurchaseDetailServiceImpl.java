package com.springboot.retailer.app.retailer_points.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceAlreadyExists;
import com.springboot.retailer.app.retailer_points.repositories.PurchaseDetailRepository;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

	private final PurchaseDetailRepository purchaseDetailRepository;

	public PurchaseDetailServiceImpl(PurchaseDetailRepository purchaseDetailRepository) {
		this.purchaseDetailRepository = purchaseDetailRepository;
	}

	@Override
	@Transactional
	public PurchaseDetail save(PurchaseDetail entity) {
		Optional<PurchaseDetail> opt = purchaseDetailRepository.findById(entity.getId());
		if (opt.isPresent()) {
			throw new ResourceAlreadyExists("PurchaseDetail already exist, ID: " + entity.getId().toString());
		}
		else {
			return purchaseDetailRepository.save(entity);
		}
	}

}
