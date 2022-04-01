package com.example.cryptowebapp.services;

import com.example.cryptowebapp.business.CryptoCurrencyList;
import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefreshStatsService {

    @Autowired
    JsonRepository jsonRepository;

    public String refresh(){
        try {
            List<CoinData> coinDataList = jsonRepository.findAll().subList((int) (jsonRepository.count() - 10), (int) jsonRepository.count());
            String json = new Gson().toJson(coinDataList);
            return json;
        }catch (Exception e){

        }
        return null;
    }


}
