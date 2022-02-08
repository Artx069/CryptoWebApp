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

@Service
@EnableScheduling
public class SaveFetchedJsonInDB {

    @Autowired
    private JsonRepository jsonRepository;


    @Scheduled(fixedRate = 30000)
    public void saveJson(){
        JsonArray jsonArray = GetJsonData.getJsonArray();

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

            jsonRepository.insert(coinData);

        }
    }

}
