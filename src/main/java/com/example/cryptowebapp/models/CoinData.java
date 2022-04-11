package com.example.cryptowebapp.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class CoinData {
    @Id
    //private long id;
    private String coinId;
    private String symbol;
    private String name;
    private List<String> current_price;
    private List<String> market_cap;

    public CoinData(String coinId, String symbol, String name, List<String> current_price, List<String> market_cap) {
        this.coinId = coinId;
        this.symbol = symbol;
        this.name = name;
        this.current_price = current_price;
        this.market_cap = market_cap;
    }

    public void addCurrentPrice(String currentPrice){
        current_price.add(currentPrice);
    }
    public void addmarketCap(String currentPrice){
        market_cap.add(currentPrice);
    }

    public String getCurrent_price() {
        return current_price.get(current_price.size() -1);
    }

    public String getMarket_cap() {
        return market_cap.get(market_cap.size()-1);
    }
}
