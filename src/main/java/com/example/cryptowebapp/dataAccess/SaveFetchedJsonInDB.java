package com.example.cryptowebapp.dataAccess;


import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@EnableScheduling
public class SaveFetchedJsonInDB {

    private static ArrayList<CoinData> restRepository;
    @Autowired
    private JsonRepository jsonRepository;



    @Scheduled(fixedRate = 5000)
    public void saveJson(){
        JsonArray jsonArray = GetJsonData.getJsonArray();
        ArrayList<CoinData> dataForRestRepository = new ArrayList<>();
        for(JsonElement e : jsonArray){
            JsonObject jsonObject = e.getAsJsonObject();
            String id = jsonObject.get("id").getAsString();
            String symbol = jsonObject.get("symbol").getAsString();
            String name = jsonObject.get("name").getAsString();
            String currentPrice = jsonObject.get("current_price").getAsString();
            String marketCap = jsonObject.get("market_cap").getAsString();

            int lastId = jsonRepository.findFirstByOrderByIdDesc().isPresent() ? (int) jsonRepository.findFirstByOrderByIdDesc().get().getId() : 0;

            CoinData coinData = new CoinData();
            coinData.setId(lastId +1);
            coinData.setCoinId(id);
            coinData.setSymbol(symbol);
            coinData.setName(name);
            coinData.setCurrent_price(new BigDecimal(currentPrice).toPlainString());
            coinData.setMarket_cap(marketCap);

            dataForRestRepository.add(coinData);
            jsonRepository.insert(coinData);

        }
        this.restRepository = dataForRestRepository;
    }

    public static ArrayList<CoinData> getCurrentCoinDataForRestController(){
        return restRepository;
    }

}
