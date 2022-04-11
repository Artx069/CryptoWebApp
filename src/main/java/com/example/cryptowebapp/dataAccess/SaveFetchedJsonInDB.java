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
import java.util.List;

@Service
@EnableScheduling
public class SaveFetchedJsonInDB {

    private static ArrayList<CoinData> restRepository;
    @Autowired
    private JsonRepository jsonRepository;



    @Scheduled(fixedRate = 30000)
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

            CoinData coinData;
            if(!jsonRepository.existsByCoinId(id)){
                coinData = new CoinData(
                        id,
                        symbol,
                        name,
                        List.of(new BigDecimal(currentPrice).toPlainString()),
                        List.of(new BigDecimal(marketCap).toPlainString()));


            }else{
                coinData = jsonRepository.getByCoinId(id).get();
                coinData.addCurrentPrice(new BigDecimal(currentPrice).toPlainString());
                coinData.addmarketCap(new BigDecimal(marketCap).toPlainString());

            }
            jsonRepository.save(coinData);
            dataForRestRepository.add(coinData);
        }
        this.restRepository = dataForRestRepository;
    }

    public static ArrayList<CoinData> getCurrentCoinDataForRestController(){
        return restRepository;
    }

    public static JsonArray getAddedCoinData(String coinId){
        JsonArray jsonArray = GetJsonData.getJsonArray();
        System.out.println(jsonArray);
        return jsonArray;
    }

}
