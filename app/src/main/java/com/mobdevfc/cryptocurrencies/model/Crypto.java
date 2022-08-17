package com.mobdevfc.cryptocurrencies.model;

public class Crypto {
    private final String symbol;
    private final String name;

    public Crypto(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

}
