package io.suriya.movie.catalog.model;

public class CatalogItem {

	private int movieId;
	private String name;
	private String overview;
	private int rating;
	
	public CatalogItem() {}

	public CatalogItem(int movieId, String name, String overview, int rating) {
		super();
		this.movieId = movieId;
		this.name = name;
		this.overview = overview;
		this.rating = rating;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
