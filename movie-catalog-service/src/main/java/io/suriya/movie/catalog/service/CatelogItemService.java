package io.suriya.movie.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.suriya.movie.catalog.model.CatalogItem;
import io.suriya.movie.catalog.model.Movie;
import io.suriya.movie.catalog.model.Rating;

@Service
public class CatelogItemService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	public CatalogItem getCatelogItems(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(),
				Movie.class);
		/*
		 * Movie movie = webClientBuilder.build() .get()
		 * .uri("http://localhost:8082/movies/"+rating.getMovieId()) .retrieve()
		 * .bodyToMono(Movie.class) .block();
		 */
		
		return new CatalogItem(movie.getMovieId(), movie.getName(), movie.getOverview(), rating.getRating());
	}

}
