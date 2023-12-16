package com.app.stock.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.stock.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
    List<Transaction> findByUser_UserId(Long userId);
    
  
	
	
}
