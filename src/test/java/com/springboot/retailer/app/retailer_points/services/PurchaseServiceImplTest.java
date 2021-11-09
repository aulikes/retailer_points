package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.dto.ClientPointsDto;
import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.exceptions.BadRequesException;
import com.springboot.retailer.app.retailer_points.repositories.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static com.springboot.retailer.app.retailer_points.DataPurchase.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PurchaseServiceImplTest {

    @MockBean
    PurchaseRepository purchaseRepository;

    @MockBean
    ClientService clientService;

    @MockBean
    ItemService itemService;

    @MockBean
    PurchaseDetailService purchaseDetailService;

    @Autowired
    PurchaseService purchaseService;

    @Test
    void saveTrue() {
        when(clientService.findById(anyLong())).thenReturn(PURCHASE_SAVE01.getClient());
        when(itemService.saveFromPurchase(any(Item.class))).thenReturn(ITEM_PUR01);
        when(purchaseDetailService.save(any(PurchaseDetail.class))).thenReturn(PURDET_SAVE01);
        when(purchaseRepository.save(any(Purchase.class))).then(new Answer<Purchase>() {
            Long sequence = 8L;
            @Override
            public Purchase answer(InvocationOnMock invocation) {
                Purchase pur = invocation.getArgument(0);
                pur.setId(sequence++);
                return pur;
            }
        });

        Purchase purchase = purchaseService.save(PURCHASE_SAVE01);
        System.out.println(purchase);
        for (PurchaseDetail p : purchase.getPurchaseDetails()) {
            System.out.println("PurchaseDetail-> PurchaseId: " + p.getPurchase().getId() + ", ItemId: " + p.getItem().getId());
        }

        assertEquals(purchase.getId(), 8L);
        assertEquals(purchase.getClient(), PURCHASE_SAVE01.getClient());
        assertEquals(purchase.getPoint(), PURCHASE_SAVE01.getPoint());
        assertEquals(purchase.getPurchaseDetails().size(), PURCHASE_SAVE01.getPurchaseDetails().size());

        verify(clientService).findById(anyLong());
        verify(itemService, times(PURCHASE_SAVE01.getPurchaseDetails().size())).saveFromPurchase(any(Item.class));
        verify(purchaseDetailService, times(PURCHASE_SAVE01.getPurchaseDetails().size())).save(any(PurchaseDetail.class));
        verify(purchaseRepository).save(any(Purchase.class));
    }

    @Test
    void saveException() {
        PURCHASE_SAVE02.getPurchaseDetails().clear();
        PURCHASE_SAVE03.setPurchaseDetails(null);

        Exception exception1 = assertThrows(BadRequesException.class, () -> purchaseService.save(PURCHASE_SAVE02));
        Exception exception2 = assertThrows(BadRequesException.class, () -> purchaseService.save(PURCHASE_SAVE03));

        verify(clientService, never()).findById(anyLong());
        verify(itemService, never()).saveFromPurchase(any(Item.class));
        verify(purchaseDetailService, never()).save(any(PurchaseDetail.class));
        verify(purchaseRepository, never()).save(any(Purchase.class));
        assertEquals(BadRequesException.class, exception1.getClass());
        assertEquals(BadRequesException.class, exception2.getClass());
    }

    @Test
    void getClientsPoints() {
        when(purchaseRepository.findPurByDateMon(anyInt(), anyInt())).thenReturn(PURCHASES_SAVE);
        List<ClientPointsDto> list = purchaseService.getClientsPoints();

        assertFalse(list.isEmpty());
        assertEquals(2, list.size());
    }

    @Test
    void getClientPoints() {
    }
}