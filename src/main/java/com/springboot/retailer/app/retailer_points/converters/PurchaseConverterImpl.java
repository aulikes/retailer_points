package com.springboot.retailer.app.retailer_points.converters;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.retailer.app.retailer_points.dto.ItemOrderDto;
import com.springboot.retailer.app.retailer_points.dto.PurchaseOrderDto;
import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.utils.PurchasePoint;

@Component
public class PurchaseConverterImpl implements PurchaseConverter {
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public PurchaseOrderDto toDto(Purchase entity) {

		PurchaseOrderDto purchaseDto = new PurchaseOrderDto();
		purchaseDto.setIdPurchase(entity.getId());
		purchaseDto.setDate(dateFormat.format(entity.getDateTime()));
		purchaseDto.setPoints(entity.getPoint());
		purchaseDto.setIdClient(entity.getClient().getId());

		List<ItemOrderDto> itemsDto = entity.getPurchaseDetails().stream().map(det -> {
			ItemOrderDto iod = new ItemOrderDto();
			iod.setCant(det.getCant());
			iod.setItemCod(det.getItem().getId());
			iod.setItemName(det.getItem().getName());
			iod.setItemValue(det.getValue());
			return iod;
		}).collect(Collectors.toList());

		purchaseDto.setItems(itemsDto);
		return purchaseDto;
	}

	@Override
	public Purchase toEntity(PurchaseOrderDto dto) {
		Client client = new Client();
		client.setId(dto.getIdClient());

		Purchase purchase = new Purchase();
		purchase.setClient(client);
		purchase.setPoint(PurchasePoint.calculatePoints(getTotal(dto.getItems())));

		List<PurchaseDetail> purDetList = dto.getItems().stream().map(det -> {
			Item item = new Item(det.getItemCod(), det.getItemName());
			return new PurchaseDetail(purchase, item, det.getItemValue(), det.getCant());
		}).collect(Collectors.toList());

		purchase.setPurchaseDetails(purDetList);

		return purchase;
	}

	@Override
	public List<PurchaseOrderDto> listEntityToDto(List<Purchase> listEntity) {
		return listEntity.stream().map(x -> toDto(x)).collect(Collectors.toList());
	}

	@Override
	public List<Purchase> listDtoToEntity(List<PurchaseOrderDto> listDto) {
		return listDto.stream().map(x -> toEntity(x)).collect(Collectors.toList());
	}

	private double getTotal(List<ItemOrderDto> list) {
//		purchaseOrder.getItems().stream().reduce((total, itemOrderDto) -> Double.sum(total, itemOrderDto.getValue()));
		return list.stream().mapToDouble(n -> n.getItemValue() * n.getCant()).sum();
	}
}
