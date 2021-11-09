package com.springboot.retailer.app.retailer_points;

import com.springboot.retailer.app.retailer_points.entities.Client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DataClient {

    public static Optional<Client> CLIENT_OPT01;
    public static Optional<Client> CLIENT_OPT02;
    public static Optional<Client> CLIENT_OPT03;

    public final static List<Client> CLIENTS;

    public final static Client CLIENT_SAVE01;
    public final static Client CLIENT_SAVE02;

    static {
        CLIENT_OPT01 = Optional.of(new Client(1L, "ANDRÉS", "PARRA"));
        CLIENT_OPT02 = Optional.of(new Client(2L, "FRANCIS", "DELOREAN"));
        CLIENT_OPT03 = Optional.of(new Client(3L, "JHON", "SMITH"));

        CLIENTS = Arrays.asList(
                CLIENT_OPT01.get(), CLIENT_OPT02.get(), CLIENT_OPT03.get());

        CLIENT_SAVE01 = new Client(100L, "JOSEPH", "MCLAUS");
        CLIENT_SAVE02 = new Client(2L, "SOPHIE", "GAMBINO");
    }
}
