package com.springboot.retailer.app.retailer_points.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetailKey;

@Repository
public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, PurchaseDetailKey> {

}
