package com.springboot.retailer.app.retailer_points.services;

import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.repositories.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.springboot.retailer.app.retailer_points.DataItem.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ItemServiceImplTest {

    @MockBean
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    void saveFromPurchaseTrue() {
        when(itemRepository.findById(anyLong())).thenReturn(Optional.empty());
        when(itemRepository.save(any(Item.class))).thenReturn(ITEM_SAVE01);

        Item item = itemService.saveFromPurchase(ITEM_SAVE01);

        assertEquals(item.getId(), ITEM_SAVE01.getId());
        assertEquals(item.getName(), ITEM_SAVE01.getName());

        verify(itemRepository).findById(anyLong());
        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void saveFromPurchase() {
        when(itemRepository.findById(anyLong())).thenReturn(ITEM_OPT01);

        Item item = itemService.saveFromPurchase(ITEM_SAVE01);

        assertEquals(item.getId(), ITEM_OPT01.get().getId());
        assertEquals(item.getName(), ITEM_OPT01.get().getName());

        verify(itemRepository).findById(anyLong());
        verify(itemRepository, never()).save(any(Item.class));
    }
}