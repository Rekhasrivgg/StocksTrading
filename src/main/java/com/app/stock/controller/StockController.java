package com.app.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.stock.entities.Stock;
import com.app.stock.exception.StockNotFound;
import com.app.stock.service.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping(path = "/save")
	public Stock createStock(@RequestBody Stock stock) {
		return stockService.saveStock(stock);
	}

	@PutMapping(path = "/update")
	public ResponseEntity<String> updateStockPrice(@RequestParam int id, @RequestParam double price)
			throws StockNotFound {
		stockService.updateStockPrice(id, price);

		return ResponseEntity.ok("Stock Updated successfully");
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePerson(@RequestParam int id) throws StockNotFound {
		stockService.deleteStock(id);
		return ResponseEntity.ok("Stock deleted successfully");
	}
}
