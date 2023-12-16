package com.app.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.stock.DTO.TransactionDTO;
import com.app.stock.entities.Transaction;
import com.app.stock.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/buy")
	public ResponseEntity<String> buyStock(@Valid @RequestBody TransactionDTO transactionDTO) {

		Transaction transaction = transactionService.buyStock(transactionDTO);

		return ResponseEntity.ok("Stock bought successfully");

	}

	@PostMapping("/sell")
	public ResponseEntity<String> sellStock(@Valid @RequestBody TransactionDTO transactionDTO) {

		Transaction transaction = transactionService.sellStock(transactionDTO);

		return ResponseEntity.ok("Stock sold successfully");

	}

	@GetMapping("/all/{id}")
	public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable Long id) {

		List<Transaction> transactions = transactionService.getAllTransactions(id);

		return ResponseEntity.ok(transactions);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable Long userId) {

		List<Transaction> transactions = transactionService.getUserTransactions(userId);
		return ResponseEntity.ok(transactions);

	}

}
