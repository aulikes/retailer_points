package com.springboot.retailer.app.retailer_points.services;

import java.util.List;

import com.springboot.retailer.app.retailer_points.entities.Client;

public interface ClientService {

	List<Client> findAll();

	Client findById(Long id);

	Client save(Client entity);
	
	Client update(Client entity);
}
