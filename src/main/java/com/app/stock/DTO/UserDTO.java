package com.app.stock.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {

	private String userName;

	@Email(message = "Invalid Email")
	private String userEmail;

	@Pattern(regexp = "^\\+91[6-9]\\d{9}$", message = "Invalid Mobile number format")
	private String userMobNo;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=.*[^\\w\\d\\s]).{8,}$", message = "Invalid password format")
	private String userPassword;

	public UserDTO(String userName, String userEmail, String userMobNo, String userPassword) {
		this.userName = userName;
		this.userEmail = userEmail;
		this.userMobNo = userMobNo;
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobNo() {
		return userMobNo;
	}

	public void setUserMobNo(String userMobNo) {
		this.userMobNo = userMobNo;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
