package com.example.cryptowebapp.services;

import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    JsonRepository jsonRepository;

    public String home(Model model){
        List<CoinData> coinDataList = jsonRepository.findAll();
        if(jsonRepository.count() >= 10) {
            coinDataList = jsonRepository.findAll().subList((int) (jsonRepository.count() - 10), (int) jsonRepository.count());
        }
        model.addAttribute(coinDataList);
        return "home";
    }
}
