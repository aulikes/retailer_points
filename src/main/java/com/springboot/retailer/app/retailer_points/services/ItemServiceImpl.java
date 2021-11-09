package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;

	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
    @Transactional
	public Item saveFromPurchase(Item item) {
		return itemRepository.findById(item.getId()).orElseGet(() -> itemRepository.save(item));
	}
}
