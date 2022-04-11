package com.example.cryptowebapp.repositories;

import com.example.cryptowebapp.models.CoinData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface JsonRepository extends MongoRepository<CoinData, String> {
    //Optional<CoinData> findFirstByOrderByIdDesc();
    Boolean existsByCoinId(String coindId);
    Optional<CoinData> getByCoinId(String coindId);

}
