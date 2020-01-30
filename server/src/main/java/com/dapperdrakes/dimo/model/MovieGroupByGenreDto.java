package com.dapperdrakes.dimo.model;

import java.util.List;

public class MovieGroupByGenreDto {
	
	private String genre;
	private List<Movie> movie;
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public List<Movie> getMovie() {
		return movie;
	}
	public void setMovie(List<Movie> movie) {
		this.movie = movie;
	}
	

}
