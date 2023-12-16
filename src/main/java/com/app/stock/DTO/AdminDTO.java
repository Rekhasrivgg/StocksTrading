package com.app.stock.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AdminDTO {

	private String adminName;

	@Email(message = "Invalid Email")
	private String adminEmail;

	@NotBlank(message = "Password is required")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[^\\w\\d\\s]).{8,}$", message = "Invalid password format")
	private String adminPassword;

	@Pattern(regexp = "^\\+91[1-9]\\d{9}$", message = "Mobile number is not valid")
	private String adminMobileno;

	public AdminDTO(String adminName, String adminEmail, String adminPassword, String adminMobileno) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminMobileno = adminMobileno;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminMobileno() {
		return adminMobileno;
	}

	public void setAdminMobileno(String adminMobileno) {
		this.adminMobileno = adminMobileno;
	}

}
