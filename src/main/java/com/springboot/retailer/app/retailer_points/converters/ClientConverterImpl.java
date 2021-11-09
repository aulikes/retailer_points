package com.springboot.retailer.app.retailer_points.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.retailer.app.retailer_points.dto.ClientDto;
import com.springboot.retailer.app.retailer_points.entities.Client;

@Component
public class ClientConverterImpl implements ClientConverter {

	@Override
	public ClientDto toDto(Client client) {
		ClientDto clientDto = new ClientDto();
		clientDto.setIdClient(client.getId());
		clientDto.setFirstName(client.getFirstName());
		clientDto.setLastName(client.getLastName());
		return clientDto;
	}

	@Override
	public Client toEntity(ClientDto clientDto) {
		Client client = new Client();
		client.setId(clientDto.getIdClient());
		client.setFirstName(clientDto.getFirstName().toUpperCase());
		client.setLastName(clientDto.getLastName().toUpperCase());
		return client;
	}

	@Override
	public List<ClientDto> listEntityToDto(List<Client> listEntity) {
		return listEntity.stream().map(x -> toDto(x)).collect(Collectors.toList());
	}

	@Override
	public List<Client> listDtoToEntity(List<ClientDto> listDto) {
		return listDto.stream().map(x -> toEntity(x)).collect(Collectors.toList());
	}
}
