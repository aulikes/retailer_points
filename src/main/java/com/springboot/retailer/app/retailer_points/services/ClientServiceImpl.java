 package com.springboot.retailer.app.retailer_points.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceAlreadyExists;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceNotFoundException;
import com.springboot.retailer.app.retailer_points.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Client findById(Long id) {
		return clientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client Not Found, ID: " + id));
	}

	@Override
    @Transactional
	public Client save(Client entity) {
		Optional<Client> opt = clientRepository.findById(entity.getId());
		if (opt.isPresent()) {
			throw new ResourceAlreadyExists("Client already exist, ID: " + entity.getId());
		}
		else {
			return clientRepository.save(entity);
		}
	}

	@Override
	@Transactional
	public Client update(Client entity) {
		return clientRepository.findById(entity.getId()).map(clientUp -> {
			clientUp.setFirstName(entity.getFirstName());
			clientUp.setLastName(entity.getLastName());
			return clientRepository.save(clientUp);
		}).orElseThrow(() -> new ResourceNotFoundException("Client Not Found, ID: " + entity.getId()));
	}
}
