package com.app.stock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.stock.DTO.AccUpdateBankNo;
import com.app.stock.DTO.AccountDTO;
import com.app.stock.entities.Account;
import com.app.stock.entities.User;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.UserNotFound;
import com.app.stock.repositories.AccountRepository;
import com.app.stock.repositories.UserRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private UserRepository userRepo;

	public static Logger logger = LoggerFactory.getLogger(AccountService.class);

	public List<Account> getAllAccounts() {
		logger.info("Fetching all accounts");
		List<Account> accList = accountRepo.findAll();
		for (Account acc : accList) {
			String maskedAccountNumber = maskBankAccount(acc.getBankAcntNo());
			acc.setBankAcntNo(maskedAccountNumber);
		}
		return accList;
	}

	public Account getAccountById(long accId) throws CustomException {

		logger.info("Fetching account with ID: {}", accId);
		Account acc = accountRepo.findById(accId).orElseThrow(() -> new CustomException(" Account Not Found!!!"));
		String maskedAccountNumber = maskBankAccount(acc.getBankAcntNo());
		acc.setBankAcntNo(maskedAccountNumber);
		logger.info("Account fetched successfully with ID: {}", accId);
		return acc;
	}

	public Account createAccount(AccountDTO accountDTO) throws CustomException, UserNotFound {

		Optional<User> opUser = userRepo.findById(accountDTO.getUserId());
		if (!opUser.isPresent()) {
			logger.error("Error Creating account. Reason: {User Not Found!!!}");
			throw new UserNotFound("User Not Found!!!");
		}

		// Check whether the PAN NO is already present or not
		else if (accountRepo.existsByPanno(accountDTO.getPanno())) {
			logger.error("Error Creating account. Reason: {An account with the same panno already exists.}");
			throw new CustomException("An account with the same panno already exists.");
		}
		
		// Check whether the BANK ACCOUNT NO is already present or not
		else if (accountRepo.existsByBankAcntNo(accountDTO.getBankAcntNo())) {
			logger.error("Error Creating account. Reason: {An account with the same bankaccNo already exists.}");
			throw new CustomException("An account with the same bankaccNo already exists.");
		}
		
		// If not then it creates the new account
		logger.info("Creating account with Info: {}", accountDTO);
		Account account = new Account();
		account.setPanno(accountDTO.getPanno());
		account.setBankAcntNo(accountDTO.getBankAcntNo());
		account.setUserId(accountDTO.getUserId());
		account.setDateOfOpening(accountDTO.getDateOfOpening());
		logger.info("Creating account with Info: {}", account);
		return accountRepo.save(account);

	}

	public Account updateAccount(long id, AccountDTO updatedAccount) throws CustomException {
		try {

			Optional<Account> optionalAccount = accountRepo.findById(id);
			Optional<User> opUser = userRepo.findById(updatedAccount.getUserId());
			if (!opUser.isPresent()) {
				logger.error("Error updating account. Reason: {Id not found for updating Account}");
				throw new UserNotFound("User Not Found!!!");
			} else if (optionalAccount.isPresent()) {
				logger.info("Updating account with ID: {}", id);
				Account existingAccount = optionalAccount.get();
				existingAccount.setUserId(updatedAccount.getUserId());
				existingAccount.setPanno(updatedAccount.getPanno());
				existingAccount.setBankAcntNo(updatedAccount.getBankAcntNo());
				existingAccount.setDateOfOpening(updatedAccount.getDateOfOpening());
				logger.info("Account updated successfully with ID: {}", id);
				return accountRepo.save(existingAccount);
			} else {
				throw new CustomException("Id not found for updating account");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CustomException("Error updating account");
		}
	}

	public Account updateBankNo(long id, AccUpdateBankNo bankno) throws CustomException {

		Optional<Account> optionalAccount = accountRepo.findById(id);
		if (optionalAccount.isPresent()) {
			logger.info("Bank account number updated successfully for account with ID: {}", id);
			Account existingAccount = optionalAccount.get();
			existingAccount.setBankAcntNo(bankno.getBankAcntNo());
			logger.info("Bank account number updated successfully for account with ID: {}", id);
			return accountRepo.save(existingAccount);
		} else {
			logger.error("Error updating account. Reason: {Id not found for updating Account No}");
			throw new CustomException("Id not found for updating Account No");
		}

	}

	public String deleteAccount(long accId) throws CustomException {
		try {
			logger.info("Deleting account with ID: {}", accId);
			Optional<Account> optionalAccount = accountRepo.findById(accId);
			if (optionalAccount.isPresent()) {
				Account accountToDelete = optionalAccount.get();
				accountRepo.delete(accountToDelete);
				logger.info("Account deleted successfully with ID: {}", accId);
				return "Account deleted successfully";
			} else {
				return "Account not found";
			}
		} catch (Exception e) {
			logger.error("Error deleting account. Reason: {}", e.getMessage());
			throw new CustomException("Error deleting account");
		}
	}

	private String maskBankAccount(String accountNumber) {
		// Check for null or empty input
		if (accountNumber == null || accountNumber.isEmpty()) {
			return "Invalid Account Number";
		}
		
		// Defining the number of visible digits and the masking character
		int visibleDigits = 4;
		char maskingCharacter = '*';

		// Calculate the number of chars to be masked
		int maskedLength = Math.max(0, accountNumber.length() - visibleDigits);
		// Creating the masked account number
		StringBuilder maskedAccountNumber = new StringBuilder();
		for (int i = 0; i < maskedLength; i++) {
			maskedAccountNumber.append(maskingCharacter);
		}
		maskedAccountNumber.append(accountNumber.substring(maskedLength));

		return maskedAccountNumber.toString();
	}
}
