package com.example.cryptowebapp.controllers;



import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.services.AddCoinIdToListService;
import com.example.cryptowebapp.services.RefreshStatsService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@AllArgsConstructor
public class RestController {

    private final RefreshStatsService refreshStatsService;

    @GetMapping("/refreshedstats")
    public String refresh(){
        String test = this.refreshStatsService.refresh();
        return this.refreshStatsService.refresh();
    }
    @GetMapping("/getRequestedCoinData")
    public String getRequestedCoinData(@RequestParam String coinId){
        System.out.println(AddCoinIdToListService.getRequestedCoinData(coinId));
        return AddCoinIdToListService.getRequestedCoinData(coinId);
    }

    @PostMapping("/addCoinId")
    public void addCoinIdToList(@RequestParam String coinId){
        AddCoinIdToListService.addCoinIdToList(coinId);
    }


}
