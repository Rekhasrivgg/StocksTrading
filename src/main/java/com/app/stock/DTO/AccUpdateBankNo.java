package com.app.stock.DTO;

import jakarta.validation.constraints.NotNull;

public class AccUpdateBankNo {

	@NotNull(message = "Bank Acc not be null")
	private String bankAcntNo;

	public AccUpdateBankNo() {

	}

	public AccUpdateBankNo(String bankAcntNo) {
		super();
		this.bankAcntNo = bankAcntNo;
	}

	public String getBankAcntNo() {
		return bankAcntNo;
	}

	public void setBankAcntNo(String bankAcntNo) {
		this.bankAcntNo = bankAcntNo;
	}

}
