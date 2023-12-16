package com.app.stock.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.stock.entities.Stock;
import com.app.stock.exception.StockNotFound;
import com.app.stock.repositories.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public Stock saveStock(Stock stock) {
		return stockRepository.save(stock);
	}

	public void updateStockPrice(long id, double price) throws StockNotFound {
		Optional<Stock> obj = stockRepository.findById(id);
		if (obj.isPresent()) {
			obj.get().setCurrentprice(price);
			stockRepository.save(obj.get());
		} else {
			throw new StockNotFound("Stock not found");
		}

	}

	public void deleteStock(long id) throws StockNotFound {
		Optional<Stock> optionalPerson = stockRepository.findById(id);
		if (optionalPerson.isPresent()) {
			stockRepository.deleteById(id);
		} else {
			throw new StockNotFound("Stock not found");
		}
	}

}
