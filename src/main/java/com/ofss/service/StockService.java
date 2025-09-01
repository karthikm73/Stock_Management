package com.ofss.service;

import com.ofss.exceptions.StockNotFoundException;
import com.ofss.model.Stock;
import com.ofss.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {



    @Autowired
    StockRepository stockRepository;

    public List<Stock> getAllStocks(){
        return (List<Stock>) stockRepository.findAll();
    }

    public Stock getAStockById(int id){
        return stockRepository.findById(id).get();
    }


    public void deleteAStockById(int sid) {
        stockRepository.deleteById(sid);
    }

    public Stock addAStock(Stock newStock) {
        return stockRepository.save(newStock);
    }

    public void updateAStockById(int sid, Stock newStock) {

        if(stockRepository.findById(sid).isPresent()){
            newStock.setStockId(sid);
            stockRepository.save(newStock);

        }
        throw new StockNotFoundException("Stock with id "+sid+" not found");

    }


    public void patchStockById(int sid, Stock partialStock) {

        Optional<Stock> optionalStock = stockRepository.findById(sid);

        if (optionalStock.isPresent()) {
            Stock existingStock = optionalStock.get();

            if (partialStock.getName() != null) {
                existingStock.setName(partialStock.getName());
            }
            if (partialStock.getPrice() != null) {
                existingStock.setPrice(partialStock.getPrice());
            }
            if (partialStock.getVolume() != null) {
                existingStock.setVolume(partialStock.getVolume());
            }
            if (partialStock.getListingPrice() != null) {
                existingStock.setListingPrice(partialStock.getListingPrice());
            }
            if (partialStock.getListedDate() != null) {
                existingStock.setListedDate(partialStock.getListedDate());
            }
            if (partialStock.getListedExchange() != null) {
                existingStock.setListedExchange(partialStock.getListedExchange());
            }

            stockRepository.save(existingStock);

        } else {
            throw new StockNotFoundException("Stock with id "+sid+" not found");      }
    }

}
