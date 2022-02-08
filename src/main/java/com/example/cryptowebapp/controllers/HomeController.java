package com.example.cryptowebapp.controllers;


import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    JsonRepository jsonRepository;

    @GetMapping("/")
    public String home(Model model){
        List<CoinData> coinDataList = jsonRepository.findAll();
        if(jsonRepository.count() >= 10) {
            coinDataList = jsonRepository.findAll().subList((int) (jsonRepository.count() - 10), (int) jsonRepository.count());
        }
        model.addAttribute(coinDataList);
        return "home";
    }
}
