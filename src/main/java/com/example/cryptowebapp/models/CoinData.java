package com.example.cryptowebapp.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CoinData {
    @Id
    private long id;
    private String coinId;
    private String symbol;
    private String name;
    private String current_price;
    private String market_cap;

}
