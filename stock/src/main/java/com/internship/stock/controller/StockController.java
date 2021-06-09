package com.internship.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.stock.enitity.SmsPojo;
import com.internship.stock.enitity.Stock;
import com.internship.stock.enitity.WrapperPojo;
import com.internship.stock.enitity.Stock_id_qty;
import com.internship.stock.repository.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@Component
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    StockService service;

    @PostMapping("/")
    public Stock addStock(@RequestBody Stock stock){
        return service.addStock(stock);
    }

    @GetMapping("/{id}")
    public WrapperPojo getStock(@PathVariable("id") Long stockId) throws IOException {
        return service.getStock(stockId);
    }

    @GetMapping("/")
    public List<WrapperPojo> getStocks() throws IOException {
        return service.getStocks();
    }
    @GetMapping("/allStocks")
    public List<Stock> getAllStocks() throws IOException {
        return service.getAllStocks();
    }

    @GetMapping("/qty")
    public List<Stock_id_qty> getStockQty(){
        return service.getStockQty();
    }

    @DeleteMapping("/delete/{id}")
    public Stock deleteStock(@PathVariable("id") Long stockId){
        return service.deleteStock(stockId);
    }

    @DeleteMapping("/delete/{id}/{qty}")
    public Stock deleteStock(@PathVariable("id") Long stockId, @PathVariable("qty") Long qty)
    {
        return service.deleteStockByQty(stockId, qty);
    }

    //@Scheduled(fixedRate = 5000)
    @GetMapping("/checkQty")
        public List<WrapperPojo> checkQty() throws IOException, URISyntaxException {
            return service.checkQty();
    }
}
