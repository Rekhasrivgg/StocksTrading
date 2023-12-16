package com.app.stock.repositories;

import org.springframework.data.repository.CrudRepository;

import com.app.stock.entities.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
	Review findById(int id);

}
