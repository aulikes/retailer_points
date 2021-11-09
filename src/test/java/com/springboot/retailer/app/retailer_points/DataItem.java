package com.springboot.retailer.app.retailer_points;

import com.springboot.retailer.app.retailer_points.entities.Item;

import java.util.Optional;

public class DataItem {

    public static Optional<Item> ITEM_OPT01;
    public final static Item ITEM_SAVE01;

    static {
        ITEM_OPT01 = Optional.of(new Item(1L, "MEAT"));
        ITEM_SAVE01 = new Item(1L, "MEAT");
    }
}
