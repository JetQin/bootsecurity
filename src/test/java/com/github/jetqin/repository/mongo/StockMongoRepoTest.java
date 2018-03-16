package com.github.jetqin.repository.mongo;


import com.github.jetqin.domain.mongodb.Stocks;
import com.github.jetqin.repository.mongodb.StockMongoRepository;
import com.github.jetqin.startup.StartupApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
//@WebAppConfiguration
@SpringBootTest(classes = StartupApplication.class)
public class StockMongoRepoTest {

    @Autowired
    @Qualifier("stockMongoRepository")
    private StockMongoRepository stockMongoRepository;


    @Before
    public void setUp() throws Exception {
        Stocks stock1= new Stocks("600000","浦发银行", new BigDecimal(23));
        Stocks stock2= new Stocks("000001","平安银行", new BigDecimal(38)) ;
        //save product, verify has ID value after save
        assertNull(stock1.getId());
        assertNull(stock2.getId());//null before save
        stockMongoRepository.save(stock1);
        stockMongoRepository.save(stock2);
        assertNotNull(stock1.getId());
        assertNotNull(stock2.getId());
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        Stocks stock = stockMongoRepository.findByCode("600000");
        assertNotNull(stock);
        assertEquals(new BigDecimal(23), stock.getTrade());
        /*Get all products, list should only have two*/
        Iterable<Stocks> stockList = stockMongoRepository.findAll();
        int count = 0;
        for(Stocks p : stockList){
            count++;
        }
        assertEquals(count, 2);
    }

    @Test
    public void testDataUpdate(){
        /*Test update*/
        Stocks stock = stockMongoRepository.findByCode("000001");
        stock.setTrade(new BigDecimal(50));
        stockMongoRepository.save(stock);
        Stocks target= stockMongoRepository.findByCode("000001");
        assertNotNull(target);
        assertEquals(new BigDecimal(50), target.getTrade());
    }

    @After
    public void tearDown() throws Exception {
        stockMongoRepository.deleteAll();
    }

}