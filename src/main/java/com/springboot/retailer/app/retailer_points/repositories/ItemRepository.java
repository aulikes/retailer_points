package com.springboot.retailer.app.retailer_points.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.retailer.app.retailer_points.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
