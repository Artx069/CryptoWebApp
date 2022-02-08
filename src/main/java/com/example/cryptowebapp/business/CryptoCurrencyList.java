package com.example.cryptowebapp.business;

import java.util.ArrayList;

public class CryptoCurrencyList {
    private static ArrayList<String> CRYPTO_CURRENCY_LIST = new ArrayList<>();

    public static String getCommaSeparatedList() {
        CRYPTO_CURRENCY_LIST.add("bitcoin");
        CRYPTO_CURRENCY_LIST.add("ethereum");
        CRYPTO_CURRENCY_LIST.add("solana");
        CRYPTO_CURRENCY_LIST.add("dogecoin");
        CRYPTO_CURRENCY_LIST.add("shiba-inu");
        CRYPTO_CURRENCY_LIST.add("cardano");
        CRYPTO_CURRENCY_LIST.add("tether");
        CRYPTO_CURRENCY_LIST.add("ripple");
        CRYPTO_CURRENCY_LIST.add("helium");
        CRYPTO_CURRENCY_LIST.add("axie-infinity");

        String commaSeparatedList = String.join("%2C", CRYPTO_CURRENCY_LIST);
        return commaSeparatedList;
    }
}
