package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetailKey;
import com.springboot.retailer.app.retailer_points.exceptions.ResourceAlreadyExists;
import com.springboot.retailer.app.retailer_points.repositories.PurchaseDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.springboot.retailer.app.retailer_points.DataPurchaseDetail.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PurchaseDetailServiceImplTest {

    @MockBean
    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @Test
    void saveTrue() {
        when(purchaseDetailRepository.findById(any(PurchaseDetailKey.class))).thenReturn(Optional.empty());
        when(purchaseDetailRepository.save(any(PurchaseDetail.class))).thenReturn(PURDET_SAVE01);

        PurchaseDetail purchaseDetail = purchaseDetailService.save(PURDET_SAVE01);

        assertEquals(purchaseDetail.getId(), PURDET_SAVE01.getId());
        assertEquals(purchaseDetail.getValue(), PURDET_SAVE01.getValue());
        assertEquals(purchaseDetail.getCant(), PURDET_SAVE01.getCant());

        verify(purchaseDetailRepository).findById(any(PurchaseDetailKey.class));
        verify(purchaseDetailRepository).save(any(PurchaseDetail.class));
    }

    @Test
    void saveException() {
        when(purchaseDetailRepository.findById(any(PurchaseDetailKey.class))).thenReturn(PURDET_OPT01);

        Exception exception = assertThrows(ResourceAlreadyExists.class, () -> purchaseDetailService.save(PURDET_SAVE01));

        verify(purchaseDetailRepository).findById(any(PurchaseDetailKey.class));
        verify(purchaseDetailRepository, never()).save(any(PurchaseDetail.class));
        assertEquals(ResourceAlreadyExists.class, exception.getClass());
    }
}