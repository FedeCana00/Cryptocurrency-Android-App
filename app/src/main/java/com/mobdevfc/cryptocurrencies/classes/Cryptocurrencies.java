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
        res.add(new Crypto("1ST", "FirstBlood"));
        res.add(new Crypto("2GIVE", "GiveCoin"));
        res.add(new Crypto("808", "808Coin"));
        res.add(new Crypto("AAVE", "Aave"));
        res.add(new Crypto("ABT", "ArcBlock"));
        res.add(new Crypto("ABY", "ArtByte"));
        res.add(new Crypto("AC", "AsiaCoin"));

        return res;
    }
}
