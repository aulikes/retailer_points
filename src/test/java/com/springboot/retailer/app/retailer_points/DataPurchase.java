package com.springboot.retailer.app.retailer_points;

import com.springboot.retailer.app.retailer_points.entities.Client;
import com.springboot.retailer.app.retailer_points.entities.Item;
import com.springboot.retailer.app.retailer_points.entities.Purchase;
import com.springboot.retailer.app.retailer_points.entities.PurchaseDetail;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class DataPurchase {

    public final static Client CLIENT_PUR01;
    public final static Client CLIENT_PUR02;

    public static final Item ITEM_PUR01;
    public static final Item ITEM_PUR02;

    public static PurchaseDetail PURDET_SAVE01;
    public static PurchaseDetail PURDET_SAVE02;

    public static Optional<Purchase> PURCHASE_OPT01;
    public static Optional<Purchase> PURCHASE_OPT02;

    public final static Purchase PURCHASE_SAVE01;
    public final static Purchase PURCHASE_SAVE02;
    public final static Purchase PURCHASE_SAVE03;
    public final static Purchase PURCHASE_SAVE04;

    public final static List<Purchase> PURCHASES;
    public final static List<Purchase> PURCHASES_SAVE;

    private final static LocalDateTime DATE_NOW;
    private final static Date DATE_ONE;
    private final static Date DATE_TWO;
    private final static Date DATE_THREE;
    private final static Date DATE_FOUR;


    static {
        DATE_NOW = LocalDateTime.of(2021, 11, 8, 13, 0, 0);
        DATE_ONE = Date.from(DATE_NOW.minusMonths(2).atZone(ZoneId.systemDefault()).toInstant());
        DATE_TWO = Date.from(DATE_NOW.minusMonths(1).atZone(ZoneId.systemDefault()).toInstant());
        DATE_THREE = Date.from(DATE_NOW.minusMonths(1).minusDays(10).atZone(ZoneId.systemDefault()).toInstant());
        DATE_FOUR = Date.from(DATE_NOW.minusMonths(2).minusDays(15).atZone(ZoneId.systemDefault()).toInstant());

        CLIENT_PUR01 = new Client(100L, "JOSEPH", "MCLAUS");
        CLIENT_PUR02 = new Client(2L, "SOPHIE", "GAMBINO");

        ITEM_PUR01 = new Item(1L, "MEAT");
        ITEM_PUR02 = new Item(2L, "FISH");

        PURCHASE_SAVE01 = new Purchase(DATE_ONE, 90L, CLIENT_PUR01, new ArrayList<>());
        PURDET_SAVE01 = new PurchaseDetail(PURCHASE_SAVE01, ITEM_PUR01, 50D, 2);
        PURDET_SAVE02 = new PurchaseDetail(PURCHASE_SAVE01, ITEM_PUR02, 20D, 1);
        PURCHASE_SAVE01.getPurchaseDetails().add(PURDET_SAVE01);
        PURCHASE_SAVE01.getPurchaseDetails().add(PURDET_SAVE02);

        PURCHASE_SAVE02 = new Purchase(DATE_TWO, 230L, CLIENT_PUR02, new ArrayList<>());
        PURDET_SAVE01 = new PurchaseDetail(PURCHASE_SAVE02, ITEM_PUR01, 50D, 3);
        PURDET_SAVE02 = new PurchaseDetail(PURCHASE_SAVE02, ITEM_PUR02, 20D, 2);
        PURCHASE_SAVE02.getPurchaseDetails().add(PURDET_SAVE01);
        PURCHASE_SAVE02.getPurchaseDetails().add(PURDET_SAVE02);

        PURCHASE_SAVE03 = new Purchase(DATE_THREE, 150L, CLIENT_PUR01, new ArrayList<>());
        PURDET_SAVE01 = new PurchaseDetail(PURCHASE_SAVE03, ITEM_PUR01, 50D, 1);
        PURDET_SAVE02 = new PurchaseDetail(PURCHASE_SAVE03, ITEM_PUR02, 20D, 5);
        PURCHASE_SAVE03.getPurchaseDetails().add(PURDET_SAVE01);
        PURCHASE_SAVE03.getPurchaseDetails().add(PURDET_SAVE02);

        PURCHASE_SAVE04 = new Purchase(DATE_FOUR, 430L, CLIENT_PUR01, new ArrayList<>());
        PURDET_SAVE01 = new PurchaseDetail(PURCHASE_SAVE04, ITEM_PUR01, 50D, 5);
        PURDET_SAVE02 = new PurchaseDetail(PURCHASE_SAVE04, ITEM_PUR02, 20D, 2);
        PURCHASE_SAVE04.getPurchaseDetails().add(PURDET_SAVE01);
        PURCHASE_SAVE04.getPurchaseDetails().add(PURDET_SAVE02);

        PURCHASE_OPT01 = Optional.of(new Purchase(1L, new Date(), 100L, CLIENT_PUR01));
        PURCHASE_OPT02 = Optional.of(new Purchase(2L, new Date(), 200L, CLIENT_PUR02));

        PURCHASES = Arrays.asList(PURCHASE_OPT01.get(), PURCHASE_OPT02.get());
        PURCHASES_SAVE = Arrays.asList(PURCHASE_SAVE01, PURCHASE_SAVE02, PURCHASE_SAVE03, PURCHASE_SAVE04);
    }
}
