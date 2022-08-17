package com.mobdevfc.cryptocurrencies.classes;

import com.mobdevfc.cryptocurrencies.model.Crypto;

import java.util.ArrayList;
import java.util.List;

public class Cryptocurrencies {

    /**
     * Get all cryptocurrencies.
     * @return returns cryto object inside list.
     */
    public static List<Crypto> get(){
        List<Crypto> res = new ArrayList<>();
        res.add(new Crypto("BUSD", "Binance-USD"));
        res.add(new Crypto("BTC", "BitCoin"));
        res.add(new Crypto("BTT", "BitTorrent"));
        res.add(new Crypto("ETH", "Ethereum"));
        res.add(new Crypto("ETHOS", "Ethos"));
        res.add(new Crypto("2GIVE", "GiveCoin"));
        return res;
    }
}
