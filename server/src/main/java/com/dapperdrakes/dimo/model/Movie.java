package com.dapperdrakes.dimo.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "Movies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements java.io.Serializable{

	@Id
	private int id;
	private String title;
	private Integer budget;
	private String homepage;
	private String origLang;
	private String origTitle;
	private String overview;
	private Double popularity;
	private Date releaseDate;
	private Long revenue;
	private Integer runtime;
	private String status;
	private String tagline;
	private Double voteAverage;
	private Integer voteCount;
	private List<Genre> genre;
	private List<Keywords> keyword;
	private List<ProdCompany> prodComp;
	private List<ProdCountry> prodCount;
	private List<SpokenLang> spoken_lang;
	
	private String poster;
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Integer getBudget() {
		return budget;
	}

	public String getHomepage() {
		return homepage;
	}

	public String getOrigLang() {
		return origLang;
	}

	public String getOrigTitle() {
		return origTitle;
	}

	public String getOverview() {
		return overview;
	}

	public Double getPopularity() {
		return popularity;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public Long getRevenue() {
		return revenue;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public String getStatus() {
		return status;
	}

	public String getTagline() {
		return tagline;
	}

	public Double getVoteAverage() {
		return voteAverage;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public List<Genre> getGenre() {
		return genre;
	}

	public List<Keywords> getKeyword() {
		return keyword;
	}

	public List<ProdCompany> getProdComp() {
		return prodComp;
	}

	public List<ProdCountry> getProdCount() {
		return prodCount;
	}

	public List<SpokenLang> getSpoken_lang() {
		return spoken_lang;
	}

	
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

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setRevenue(Long revenue) {
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

	public void setVoteAverage(Double voteAverage) {
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
