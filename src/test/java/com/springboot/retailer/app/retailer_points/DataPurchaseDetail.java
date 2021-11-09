package com.springboot.retailer.app.retailer_points;

import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;

import java.util.Date;
import java.util.Optional;

public class DataPurchaseDetail {
    public static final Item ITEM_PUR01;
    public static final Item ITEM_PUR02;

    public final static Client CLIENT_PUR01;
    public final static Client CLIENT_PUR02;

    public final static Purchase PURCHASE_SAVE01;
    public final static Purchase PURCHASE_SAVE02;

    public static Optional<PurchaseDetail> PURDET_OPT01;
    public static Optional<PurchaseDetail> PURDET_OPT02;

    public static PurchaseDetail PURDET_SAVE01;
    public static PurchaseDetail PURDET_SAVE02;

    static {
        ITEM_PUR01 = new Item(1L, "MEAT");
        ITEM_PUR02 = new Item(2L, "FISH");

        CLIENT_PUR01 = new Client(100L, "JOSEPH", "MCLAUS");
        CLIENT_PUR02 = new Client(2L, "SOPHIE", "GAMBINO");

        PURCHASE_SAVE01 = new Purchase(1L, new Date(), 100L, CLIENT_PUR01);
        PURCHASE_SAVE02 = new Purchase(2L, new Date(), 100L, CLIENT_PUR02);

        PURDET_OPT01 = Optional.of(new PurchaseDetail(PURCHASE_SAVE01, ITEM_PUR01, 100D, 5));
        PURDET_OPT02 = Optional.of(new PurchaseDetail(PURCHASE_SAVE02, ITEM_PUR02, 100D, 2));

        PURDET_SAVE01 = new PurchaseDetail(PURCHASE_SAVE01, ITEM_PUR01, 100D, 5);
        PURDET_SAVE02 = new PurchaseDetail(PURCHASE_SAVE02, ITEM_PUR02, 100D, 2);
    }
}
