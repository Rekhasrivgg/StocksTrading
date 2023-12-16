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

import com.app.stock.DTO.UserDTO;
import com.app.stock.DTO.UserDTOLogin;
import com.app.stock.entities.User;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.exception.UserNotFound;
import com.app.stock.service.ReviewService;
import com.app.stock.service.UserService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	ReviewService reviewService;

	@PostMapping(path = "/create")
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDTO user, BindingResult bindingResult)
			throws CustomException {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Object>("Invalid input", HttpStatus.CONFLICT);
		}
		User u1 = userService.createUser(user);
		return new ResponseEntity<>(u1, HttpStatus.CREATED);

	}

	@DeleteMapping
	public ResponseEntity<Object> deleteUser(@RequestParam long id) throws UserNotFound {
		userService.deleteUser(id);
		return new ResponseEntity<>("User Delete Successfully", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Object> loginUser(@Valid @RequestBody UserDTOLogin userLog)
			throws UserNotFound, PasswordMissmatchException {
		User user = userService.loginUser(userLog);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserDTO user)
			throws UserNotFound {
		User updatedUser = userService.updateUser(id, user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getUserById(@PathVariable long id) throws UserNotFound {
		User user = userService.getUserById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<Object> getAllUsers() {
		List<User> list = userService.getAllUsers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
