package com.app.stock.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AccountDTO {

	@NotBlank(message = "user id cannot be blank")
	private Long userId;

	@NotNull(message = "Panno not be null")
	@Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN format")
	private String panno;

	@NotNull(message = "Bank Acc not be null")
	private String bankAcntNo;

	private LocalDate dateOfOpening;

	public AccountDTO(Long userId, String panno, String bankAcntNo, LocalDate dateOfOpening) {
		this.userId = userId;
		this.panno = panno;
		this.bankAcntNo = bankAcntNo;
		this.dateOfOpening = dateOfOpening;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
