package com.app.stock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.app.stock.DTO.UserDTO;
import com.app.stock.DTO.UserDTOLogin;
import com.app.stock.entities.User;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.PasswordMissmatchException;
import com.app.stock.exception.UserNotFound;
import com.app.stock.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
@Validated
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public static Logger logger = LoggerFactory.getLogger(UserService.class);

	public User createUser(@Valid UserDTO user) throws CustomException {

		try {
			logger.info("Creating a new user with email: {}", user.getUserEmail());
			// Validate if username is unique
			if (userRepo.existsByUserEmail(user.getUserEmail())) {
				throw new CustomException("Username already exists");
			}
			User u1 = new User();
			u1.setUserName(user.getUserName());
			u1.setUserEmail(user.getUserEmail().toLowerCase());
			u1.setUserPassword(user.getUserPassword());
			u1.setUserMobNo(user.getUserMobNo());
			logger.info("User created successfully with email: {}", user.getUserEmail());
			return userRepo.save(u1);
		} catch (CustomException e) {
			logger.warn("Failed to create user. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public void deleteUser(long id) throws UserNotFound {
		try {
			logger.info("Deleting user with ID: {}", id);

			User user = userRepo.findById(id).orElseThrow(() -> new UserNotFound("Not Found"));
			logger.info("User deleted successfully with ID: {}", id);
			userRepo.deleteById(id);
		} catch (UserNotFound e) {
			logger.warn("Failed to delete user. Reason: {}", e.getMessage());
			throw e;
		}

	}

	public User loginUser(@Valid UserDTOLogin userLog) throws UserNotFound, PasswordMissmatchException {
		try {
			logger.info("Logging in user with email: {}", userLog.getUserEmail());

			User user = userRepo.findByUserEmail(userLog.getUserEmail())
					.orElseThrow(() -> new UserNotFound("User not found"));

			if (!user.getUserPassword().equals(userLog.getUserPassword())) {
				throw new PasswordMissmatchException("Invalid password");
			}

			logger.info("User logged in successfully with email: {}", userLog.getUserEmail());
			return user;
		} catch (UserNotFound | PasswordMissmatchException e) {
			logger.warn("Failed to log in user. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public User updateUser(@Valid Long id, UserDTO user) throws UserNotFound {

		try {
			logger.info("Updating user with ID: {}", id);

			User existingUser = userRepo.findById(id).orElseThrow(() -> new UserNotFound("User not found"));

			existingUser.setUserName(user.getUserName());
			existingUser.setUserEmail(user.getUserEmail());
			existingUser.setUserPassword(user.getUserPassword());
			existingUser.setUserMobNo(user.getUserMobNo());

			logger.info("User updated successfully with ID: {}", id);
			return userRepo.save(existingUser);
		} catch (UserNotFound e) {
			logger.warn("Failed to update user. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public User getUserById(long id) throws UserNotFound {
		try {
			logger.info("Fetching user with ID: {}", id);
			Optional<User> user = userRepo.findById(id);
			logger.info("Fetched user successfully with ID: {}", id);
			return user.orElseThrow(() -> new UserNotFound("User Not Found"));
		} catch (UserNotFound e) {
			logger.warn("Failed to fetch user. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public List<User> getAllUsers() {
		logger.info("Fetched all users successfully");
		return userRepo.findAll();
	}

}
