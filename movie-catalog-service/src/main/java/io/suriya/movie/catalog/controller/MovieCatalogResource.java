package io.suriya.movie.catalog.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.suriya.movie.catalog.model.CatalogItem;
import io.suriya.movie.catalog.model.Movie;
import io.suriya.movie.catalog.model.Rating;
import io.suriya.movie.catalog.model.UserRating;
import io.suriya.movie.catalog.service.CatelogItemService;
import io.suriya.movie.catalog.service.UserRatingService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CircuitBreakerFactory<?, ?> circuitBreakerFactory;
	
	@Autowired
	private UserRatingService userRatingService;
	
	@Autowired
	private CatelogItemService catelogItemService;

	@GetMapping("/{userId}")
	public List<CatalogItem> getCatelog(@PathVariable("userId") String userId) {

		CircuitBreaker cb = circuitBreakerFactory.create("catelog-circuit-breaker");

		UserRating userRatings = userRatingService.getUserRatingData(cb, userId);
		
		return userRatings.getUserRating().stream()
				.map(rating -> catelogItemService.getCatelogItems(rating))
				.collect(Collectors.toList());
	}
}
