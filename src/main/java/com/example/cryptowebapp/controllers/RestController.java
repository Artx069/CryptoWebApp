package com.example.cryptowebapp.controllers;



import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.services.RefreshStatsService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

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
}
