package com.app.stock.DTO;

import jakarta.validation.constraints.NotNull;

public class TransactionDTO {

	public TransactionDTO() {
	}

	@NotNull
	private Long userId;
	@NotNull
	private Long stockId;
	@NotNull
	private int quantity;

	public TransactionDTO(Long userId, Long stockId, int quantity) {
		
		this.userId = userId;
		this.stockId = stockId;
		this.quantity = quantity;
	}

	// Constructors, getters, and setters

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}