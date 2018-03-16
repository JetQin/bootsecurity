package com.github.jetqin.repository.mongodb;

import com.github.jetqin.domain.mongodb.Stocks;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("stockMongoRepository")
public interface StockMongoRepository extends MongoRepository<Stocks,String>
{
    Stocks findByName(String name);

    Stocks findByCode(String code);
}
