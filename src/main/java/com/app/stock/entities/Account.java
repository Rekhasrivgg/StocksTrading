package com.app.stock.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long accId;

	private long userId;

	@NotNull(message = "Panno not be null")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
	private String panno;

	@NotNull(message = "Account No. not be null")
	private String bankAcntNo;

	private LocalDate dateOfOpening;

	public Account() {
	}

	public Account(long userId, String panno, String bankAcntNo, LocalDate dateOfOpening) {
		
		this.userId = userId;
		this.panno = panno;
		this.bankAcntNo = bankAcntNo;
		this.dateOfOpening = dateOfOpening;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getAccId() {
		return accId;
	}

	public void setAccId(long accId) {
		this.accId = accId;
	}

	public String getPanno() {
		return panno;
	}

	public void setPanno(String panno) {
		this.panno = panno;
	}

	public String getBankAcntNo() {
		return bankAcntNo;
	}

	public void setBankAcntNo(String bankAcntNo) {
		this.bankAcntNo = bankAcntNo;
	}

	public LocalDate getDateOfOpening() {
		return dateOfOpening;
	}

	public void setDateOfOpening(LocalDate dateOfOpening) {
		this.dateOfOpening = dateOfOpening;
	}
	
}
