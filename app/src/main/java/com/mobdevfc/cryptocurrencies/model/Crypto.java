package com.mobdevfc.cryptocurrencies.model;

/***
 * This class represents the Crypto model.
 */
public class Crypto {
    private final String symbol;
    private final String name;

    /**
     * @param symbol represents the crypto identifier.
     * @param name represents the crypto name.
     */
    public Crypto(String symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * Getter.
     * @return returns crypt symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Getter.
     * @return returns crypt name.
     */
    public String getName() {
        return name;
    }

}
