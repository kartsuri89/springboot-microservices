package io.suriya.movie.catalog.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.suriya.movie.catalog.model.Rating;
import io.suriya.movie.catalog.model.UserRating;

@Service
public class UserRatingService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public UserRating getUserRatingData(CircuitBreaker cb,String userId) {
		/*
		 * UserRating userRatings = restTemplate
		 * .getForObject("http://rating-data-service/ratingsdata/users/"+userId,
		 * UserRating.class);
		 */
		
		return (UserRating) cb.run(() -> restTemplate
				.getForObject("http://rating-data-service/ratingsdata/users/" + userId, UserRating.class),
				throwable -> getDefaultUserRating());
	}
	
	private UserRating getDefaultUserRating() {
		List<Rating> ratings = Arrays.asList(new Rating("002", 5), new Rating("002", 5));
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;

	}

}
