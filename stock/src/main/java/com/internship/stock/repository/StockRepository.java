package com.internship.stock.repository;

import com.internship.stock.enitity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByStockId(Long stockId);
}
