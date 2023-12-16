package com.app.stock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.stock.entities.Review;
import com.app.stock.exception.CustomException;
import com.app.stock.exception.ReviewNotFoundException;
import com.app.stock.service.ReviewService;

@RestController
@RequestMapping("/review")
public class ReviewController {
	@Autowired
	ReviewService reviewService;

	@GetMapping("/all")
	public ResponseEntity<List<Review>> getAllReviews() throws ReviewNotFoundException {
		return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
	}

	@PostMapping("/save")
	public Review saveReview(@RequestParam Long userId, @RequestParam int Rating, @RequestParam String Feedback)
			throws ReviewNotFoundException, CustomException {
		Review r = new Review();
		r.setRating(Rating);
		r.setFeedback(Feedback);
		r.setUserId(userId);
		return reviewService.saveReview(r);
	}

	@DeleteMapping("/delete={id}")
	public ResponseEntity<String> deleteReview(@PathVariable int id) throws ReviewNotFoundException {

		reviewService.deleteReview(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> getReview(@PathVariable int id) throws ReviewNotFoundException {
		return new ResponseEntity<>(reviewService.getReview(id), HttpStatus.OK);
	}

}
