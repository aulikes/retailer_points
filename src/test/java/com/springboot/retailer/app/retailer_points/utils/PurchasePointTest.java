package com.springboot.retailer.app.retailer_points.utils;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PurchasePointTest {

    @ParameterizedTest(name = "index {index}, totalVend: {0}; totalExpect: {1}")
    @CsvSource({"120,90", "250,350", "300,450", "510,870"})
    void calculatePoints(double totalVend, double totalExpect) {
        long calcu = PurchasePoint.calculatePoints(totalVend);
        assertEquals(calcu, totalExpect);
    }
}