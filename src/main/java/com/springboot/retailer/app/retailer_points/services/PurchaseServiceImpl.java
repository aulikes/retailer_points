package com.springboot.retailer.app.retailer_points.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.exceptions.BadRequesException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.retailer.app.retailer_points.dto.ClientPointsDto;
import com.springboot.retailer.app.retailer_points.dto.PointMonthDto;
import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceNotFoundException;
import com.springboot.retailer.app.retailer_points.repositories.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseRepository purchaseRepository;
	private final ClientService clientService;
	private final ItemService itemService;
	private final PurchaseDetailService purchaseDetailService;

	public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ClientService clientService,
			ItemService itemService, PurchaseDetailService purchaseDetailService) {
		super();
		this.purchaseRepository = purchaseRepository;
		this.clientService = clientService;
		this.itemService = itemService;
		this.purchaseDetailService = purchaseDetailService;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Purchase save(Purchase entity) {
		if (entity.getPurchaseDetails() == null || entity.getPurchaseDetails().isEmpty())
			throw new BadRequesException("The purchase must have associated items.");

		clientService.findById(entity.getClient().getId());
		Purchase purchaseSave = purchaseRepository.save(entity);

		for (PurchaseDetail p : entity.getPurchaseDetails()) {
			itemService.saveFromPurchase(p.getItem());
			purchaseDetailService.save(p);
		}
		return purchaseSave;
	}

	@Override
	public List<ClientPointsDto> getClientsPoints() {
		return getClientPoints(purchaseRepository.findPurByDateMon(3, 1));
	}

	@Override
	public ClientPointsDto getClientPoints(Long idClient) {
		Client client = new Client();
		client.setId(idClient);
		
		List<ClientPointsDto> clientPoint = getClientPoints(purchaseRepository.findPurByDateMonCli(3, 1, client));
		if (clientPoint.size() == 0)
			throw new ResourceNotFoundException("The client has no purchases established in the last three months");
		return clientPoint.get(0);
	}
	
	private List<ClientPointsDto> getClientPoints(List<Purchase> puchases) {
		List<ClientPointsDto> clientPoint = new ArrayList<ClientPointsDto>();

		for (Map.Entry<Client, List<PointMonthDto>> entry : puchases.stream()
				.collect(Collectors.groupingBy(pur -> pur.getClient(), Collectors.mapping(map -> {
					PointMonthDto point = new PointMonthDto();
					point.setMonth(getDateMonth(map.getDateTime()));
					point.setPoints(map.getPoint());
					return point;
				}, Collectors.toList()))).entrySet()) {
			Client key = entry.getKey();
			List<PointMonthDto> list = entry.getValue();
			ClientPointsDto cp = new ClientPointsDto();
			cp.setIdClient(key.getId());
			cp.setFirstName(key.getFirstName());
			cp.setLastName(key.getLastName());
			cp.setPointsMonth(new ArrayList<>());

			Map<String, Long> m = list.stream().collect(Collectors.groupingBy(pur -> pur.getMonth(),
					Collectors.summingLong(pur -> pur.getPoints())));

			m.forEach((month, point) -> {
				PointMonthDto pointDto = new PointMonthDto();
				pointDto.setMonth(month);
				pointDto.setPoints(point);
				cp.getPointsMonth().add(pointDto);
				cp.sumPoints(point);
			});
			clientPoint.add(cp);
		}
		return clientPoint;
	}

	private String getDateMonth(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(date);
	}
}
