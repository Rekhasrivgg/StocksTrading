package com.app.stock.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.stock.DTO.AccUpdateBankNo;
import com.app.stock.DTO.AccountDTO;
import com.app.stock.entities.Account;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.UserNotFound;
import com.app.stock.service.AccountService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/accounts")
public class AccountController {

	public static Logger lg = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@GetMapping("/all")
	public ResponseEntity<List<Account>> getAllAccounts() {
		lg.info("Fetching all accounts");
		List<Account> accounts = accountService.getAllAccounts();
		lg.debug("Fetched accounts: {}", accounts);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/{accId}")
	public ResponseEntity<Object> getAccountById(@PathVariable long accId) throws CustomException {
		lg.info("Fetching account by id");
		Account acc = accountService.getAccountById(accId);
		lg.debug("Fetched account: {}", acc);
		return new ResponseEntity<Object>(acc, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountDTO accountDTO)
			throws CustomException, UserNotFound {
		lg.info("Creating a new Account");
		Account account = accountService.createAccount(accountDTO);
		lg.debug("Account Created: {}", account);
		return new ResponseEntity<>(account, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateAccount(@Valid @PathVariable long id, @RequestBody AccountDTO updatedAccount)
			throws CustomException {
		lg.info("Update account by id");
		Account account = accountService.updateAccount(id, updatedAccount);
		lg.debug("Account Created: {}", account);
		return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
	}

	@PutMapping("/upbank/{id}")
	public ResponseEntity<Object> updateBankNo(@Valid @PathVariable long id,
			@RequestBody AccUpdateBankNo updatedAccount) throws CustomException {
		lg.info("Updating BankAccount in account by id");
		Account account = accountService.updateBankNo(id, updatedAccount);
		lg.debug("Account Created: {}", account);
		return new ResponseEntity<>(account, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{accId}")
	public ResponseEntity<Object> deleteAccount(@PathVariable long accId) throws CustomException {
		String result = accountService.deleteAccount(accId);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}
}
