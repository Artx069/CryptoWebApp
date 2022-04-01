package com.example.cryptowebapp.services;

import com.example.cryptowebapp.business.CryptoCurrencyList;
import com.example.cryptowebapp.dataAccess.SaveFetchedJsonInDB;
import com.example.cryptowebapp.models.CoinData;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

public class AddCoinIdToListService {
    public static void addCoinIdToList(String coinId){
        System.out.println("coin added: " + coinId);
        CryptoCurrencyList.addCoin(coinId);
    }

    public static String getRequestedCoinData(String coinId){
        System.out.println("getReqestedCoinData: " + coinId);
        JsonArray jsonArrayCoinDataList = SaveFetchedJsonInDB.getAddedCoinData(coinId);
        String json = new Gson().toJson(jsonArrayCoinDataList);
        return json;
    }
}
