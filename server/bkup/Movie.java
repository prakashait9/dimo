package com.dapperdrakes.dimo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "movie")
public class Movie {

	@Id
	private int id;
	private String title;
	private Integer budget;
	private String homepage;
	private String origLang;
	private String origTitle;
	private String overview;
	private BigDecimal popularity;
	private LocalDateTime releaseDate;
	private Integer revenue;
	private Integer runtime;
	private String status;
	private String tagline;
	private BigDecimal voteAverage;
	private Integer voteCount;
	private List<Genre> genre;
	private List<Keywords> keyword;
	private List<ProdCompany> prodComp;
	private List<ProdCountry> prodCount;
	private List<SpokenLang> spoken_lang;

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public void setOrigLang(String origLang) {
		this.origLang = origLang;
	}

	public void setOrigTitle(String origTitle) {
		this.origTitle = origTitle;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public void setPopularity(BigDecimal popularity) {
		this.popularity = popularity;
	}

	public void setReleaseDate(LocalDateTime releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRevenue(Integer revenue) {
		this.revenue = revenue;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public void setVoteAverage(BigDecimal voteAverage) {
		this.voteAverage = voteAverage;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}

	

	public void setKeyword(List<Keywords> keyword) {
		this.keyword = keyword;
	}

	public void setProdComp(List<ProdCompany> prodComp) {
		this.prodComp = prodComp;
	}

	public void setProdCount(List<ProdCountry> prodCount) {
		this.prodCount = prodCount;
	}

	public void setSpoken_lang(List<SpokenLang> spoken_lang) {
		this.spoken_lang = spoken_lang;
	}

}
