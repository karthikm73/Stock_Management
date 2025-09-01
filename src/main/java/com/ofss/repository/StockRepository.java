package com.ofss.repository;

import com.ofss.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer>{
    // no need to write anything here
}
