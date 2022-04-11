package com.example.cryptowebapp.business;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class CryptoCurrencyList {
    private static ArrayList<String> CRYPTO_CURRENCY_LIST = new ArrayList<>();
    @PostConstruct
    private void initializeList(){
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
        System.out.println("postconstruct done");
    }

    public static String getCommaSeparatedList() {
        String commaSeparatedList = String.join("%2C", CRYPTO_CURRENCY_LIST);
        return commaSeparatedList;
    }

    public static ArrayList<String> getCRYPTO_CURRENCY_LIST(){
        return CRYPTO_CURRENCY_LIST;
    }

    public static void addCoin(String coinID){
        if(!CRYPTO_CURRENCY_LIST.contains(coinID)) {
            CRYPTO_CURRENCY_LIST.add(coinID);
        }
    }
}
