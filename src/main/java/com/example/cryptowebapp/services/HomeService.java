package com.example.cryptowebapp.services;

import com.example.cryptowebapp.business.CryptoCurrencyList;
import com.example.cryptowebapp.models.CoinData;
import com.example.cryptowebapp.repositories.JsonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    JsonRepository jsonRepository;

    public String home(Model model){
        List<CoinData> coinDataList = new ArrayList<>();
        for(String coindId : CryptoCurrencyList.getCRYPTO_CURRENCY_LIST()){
            coinDataList.add(jsonRepository.findById(coindId).get());
        }

        model.addAttribute(coinDataList);
        return "home";
    }
}
