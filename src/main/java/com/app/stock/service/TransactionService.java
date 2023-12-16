package com.app.stock.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.stock.DTO.TransactionDTO;
import com.app.stock.entities.Stock;
import com.app.stock.entities.Transaction;
import com.app.stock.entities.User;
import com.app.stock.repositories.StockRepository;
import com.app.stock.repositories.TransactionRepository;
import com.app.stock.repositories.UserRepository;

@Service
public class TransactionService{
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private StockRepository stockRepository;
    
    
    public Transaction buyStock(TransactionDTO transactionDTO) {
    	
    	 User user = userRepository.findById(transactionDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
         Stock stock = stockRepository.findById(transactionDTO.getStockId()).orElseThrow(() -> new RuntimeException("Stock not found"));
    	 
    	 if (stock.getStockquantity() < transactionDTO.getQuantity()) {
             // Not enough quantity available in stock
             throw new RuntimeException("Quantity not available");
         }
    	 
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setStock(stock);
        transaction.setPrice(stock.getCurrentprice());
        transaction.setQuantity(transactionDTO.getQuantity());
        
        stock.setStockquantity(stock.getStockquantity() - transactionDTO.getQuantity());
        
        transaction.setTransactionDate(LocalDateTime.now());
       
        transaction.setTransactionType("BUY");
  
        
        
        stockRepository.save(stock);
        
        return transactionRepository.save(transaction);
    }
      
    
    public Transaction sellStock(TransactionDTO transactionDTO) {
    	 User user = userRepository.findById(transactionDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
         Stock stock = stockRepository.findById(transactionDTO.getStockId()).orElseThrow(() -> new RuntimeException("Stock not found"));
    	 
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setStock(stock);
        transaction.setPrice(stock.getCurrentprice());
        transaction.setQuantity(transactionDTO.getQuantity());
        
        stock.setStockquantity(stock.getStockquantity() + transactionDTO.getQuantity());
        
        transaction.setTransactionDate(LocalDateTime.now());
                
        transaction.setTransactionType("SELL");
        
        
        stockRepository.save(stock);

        return transactionRepository.save(transaction);
    }
    
    
    public List<Transaction> getAllTransactions(Long id) {
        // Implement logic for retrieving user transactions
        return transactionRepository.findAll();
    }
    
    public List<Transaction> getUserTransactions(Long userId) {
        return transactionRepository.findByUser_UserId(userId);
    }


}