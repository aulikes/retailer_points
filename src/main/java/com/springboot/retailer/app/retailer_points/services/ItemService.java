package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.entities.Item;

public interface ItemService{

	Item saveFromPurchase(Item item);
}
