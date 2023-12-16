package com.app.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.stock.DTO.AdminDTO;
import com.app.stock.DTO.AdminDTOLogin;
import com.app.stock.DTO.UserDTO;
import com.app.stock.entities.Account;
import com.app.stock.entities.AdminEntity;
import com.app.stock.entities.Review;
import com.app.stock.entities.Stock;
import com.app.stock.entities.User;
import com.app.stock.exception.AdminNotFound;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.MobileNumberException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.exception.ReviewNotFoundException;
import com.app.stock.exception.StockNotFound;
import com.app.stock.exception.UserNotFound;
import com.app.stock.service.AccountService;
import com.app.stock.service.AdminService;
import com.app.stock.service.ReviewService;
import com.app.stock.service.StockService;
import com.app.stock.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private StockService stockService;

	@Autowired
	private UserService userService;

	@Autowired
	private AccountService accountService;

	@PostMapping("/create")
	public ResponseEntity<Object> createAdmin(@Valid @RequestBody AdminDTO admin, BindingResult bindingResult)
			throws CustomException, MobileNumberException {
		// Validation check
		if (bindingResult.hasErrors()) {
			throw new CustomException("You entered Invalid Input");
		} else {
			AdminEntity createdAdmin = adminService.createAdmin(admin);
			return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
		}
	}

	@GetMapping("/login")
	public ResponseEntity<Object> loginAdmin(@Valid @RequestBody AdminDTOLogin admin)
			throws AdminNotFound, PasswordMissmatchException, MobileNumberException {
		AdminEntity adminLog = adminService.loginAdmin(admin);
		return new ResponseEntity<>(adminLog, HttpStatus.CONTINUE);
	}

	@GetMapping("/id={id}")
	public ResponseEntity<Object> getAdmin(Long id) throws AdminNotFound {
		AdminEntity admin = adminService.getAdmin(id);
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateAdmin(@PathVariable("id") Long id, @Valid @RequestBody AdminDTO admin)
			throws AdminNotFound {
		AdminEntity adminUp = adminService.updateAdmin(id, admin);
		return new ResponseEntity<>(adminUp, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllAdmin() {
		List<AdminEntity> list = adminService.getAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@DeleteMapping("/delete/adminbyid")
	public ResponseEntity<Object> deleteAdmin(@RequestParam long id) throws AdminNotFound {
		adminService.deleteAdmin(id);
		return new ResponseEntity<>("Admin Delete Successfully", HttpStatus.OK);
	}

	@GetMapping("/account/all")
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@GetMapping("/account/id/{accId}")
	public ResponseEntity<Object> getAccountById(@PathVariable long accId) throws CustomException {
		Account acc = accountService.getAccountById(accId);
		return new ResponseEntity<>(acc, HttpStatus.OK);
	}

	@GetMapping("/review/all")
	public ResponseEntity<List<Review>> getAllReviews() throws ReviewNotFoundException {
		return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
	}

	@GetMapping("/review/id/{id}")
	public ResponseEntity<Review> getReview(@PathVariable int id) throws ReviewNotFoundException {
		return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
	}

	@PostMapping(path = "/stock/save")
	public Stock createStock(@Valid @RequestBody Stock stock) {
		return stockService.saveStock(stock);
	}

	@PutMapping(path = "/stock/update")
	public ResponseEntity<String> updateStockPrice(@RequestParam int id, @RequestParam double price)
			throws StockNotFound {
		stockService.updateStockPrice(id, price);
		return ResponseEntity.ok("Stock Updated successfully");
	}

	@DeleteMapping("/stock/delete")
	public ResponseEntity<String> deletePerson(@RequestParam int id) throws StockNotFound {
		stockService.deleteStock(id);
		return ResponseEntity.ok("Stock deleted successfully");
	}

	@DeleteMapping("/user/delete")
	public ResponseEntity<Object> deleteUser(@RequestParam long id) throws UserNotFound {
		userService.deleteUser(id);
		return new ResponseEntity<>("User Delete Successfully", HttpStatus.OK);
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO user)
			throws UserNotFound {
		User updatedUser = userService.updateUser(id, user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/user/get/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable long id) throws UserNotFound {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping(path = "/user/all")
	public ResponseEntity<Object> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}