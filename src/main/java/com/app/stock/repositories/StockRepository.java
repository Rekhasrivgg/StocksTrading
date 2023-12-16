package com.app.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.stock.entities.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
