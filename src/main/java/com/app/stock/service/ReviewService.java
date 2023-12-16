package com.app.stock.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.stock.entities.Review;
import com.app.stock.entities.User;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.ReviewNotFoundException;
import com.app.stock.repositories.ReviewRepository;
import com.app.stock.repositories.UserRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository repo;

	@Autowired
	private UserRepository userRepo;
	
	public static Logger logger = LoggerFactory.getLogger(ReviewService.class);

	public Review saveReview(Review r) throws ReviewNotFoundException, CustomException {
		Optional<User> user = userRepo.findById(r.getUserId());
		logger.info("Saving a new review");
		if (repo.existsById(r.getId())) {
			logger.warn("Failed to save review. Reason: {Review Already Exist}");
			throw new ReviewNotFoundException("Review Already Exist");
		} else if (user.isPresent()) {
			logger.warn("Failed to save review. Reason: {User Already Given a Rating}");
			throw new CustomException("User Already Given a Rating");
		} else {
			if (r.getRating() <= 10) {
				logger.info("Review saved successfully");
				return repo.save(r);
			} else {
				throw new ReviewNotFoundException("Please Enter Valid Rating");
			}
		}
	}

	public String deleteReview(int id) throws ReviewNotFoundException {
		try {
			logger.info("Deleting review with ID: {}", id);
		if (repo.existsById(id)) {
			repo.deleteById(id);
			logger.info("Review deleted successfully with ID: {}", id);
			return "Review deleted";
		} else {
			throw new ReviewNotFoundException("Review does not Exist");
		}
		}
		catch (ReviewNotFoundException e) {
			logger.warn("Failed to delete review. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public List<Review> getAllReviews() throws ReviewNotFoundException {
		try {
			logger.info("Fetching all reviews");
		if (repo.count() == 0) {
			throw new ReviewNotFoundException("Reviews does not Exist");
		} else {
			logger.info("Fetched all reviews successfuly");
			return (List<Review>) repo.findAll();
		}
		} catch (ReviewNotFoundException e) {
			logger.warn("Failed to fetch all reviews. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public String deleteAllReviews() throws ReviewNotFoundException {
		try {
			logger.info("Deleting all reviews");
		if (repo.count() == 0) {
			throw new ReviewNotFoundException("Reviews does not Exist");
		} else {
			logger.info("All reviews deleted successfully");
			repo.deleteAll();
			return "All Reviews Deleted";
		}
		}
	    catch (ReviewNotFoundException e) {
			logger.warn("Failed to delete all reviews. Reason: {}", e.getMessage());
			throw e;
		}
	}

	public Review getReview(int id) throws ReviewNotFoundException {
		try {
			logger.info("Fetching review with ID: {}", id);
		if (repo.existsById(id)) {
			logger.info("Fetched review successfully with ID: {}", id);
			return repo.findById(id);
		} else {
			
			throw new ReviewNotFoundException("Reviews does not Exist");
		} 
	    }
	     catch (ReviewNotFoundException e) {
			logger.warn("Failed to fetch review. Reason: {}", e.getMessage());
			throw e;
		}
	}
}
