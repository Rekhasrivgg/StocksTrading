package com.app.stock.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "adminData")

public class AdminEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long adminId;
	private String adminName;
	private String adminEmail;
	private String adminPassword;
	private String adminMobileno;

	public AdminEntity() {
	}

	public AdminEntity(String adminName, String adminEmail, String adminPassword, String adminMobileno) {
		this.adminName = adminName;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
		this.adminMobileno = adminMobileno;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
	 	this.adminId = adminId;
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
