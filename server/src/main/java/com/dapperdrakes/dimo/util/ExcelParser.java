package com.dapperdrakes.dimo.util;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dhatim.fastexcel.reader.Row;
import org.springframework.beans.factory.annotation.Autowired;

import com.dapperdrakes.dimo.model.Genre;
import com.dapperdrakes.dimo.model.Keywords;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.model.ProdCompany;
import com.dapperdrakes.dimo.model.ProdCountry;
import com.dapperdrakes.dimo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelParser {
	static Set<Genre> genreList = new HashSet<>();
	static Set<Keywords> keyWordsList = new HashSet<>();
	static Set<ProdCompany> prodCompList = new HashSet<>();
	static Set<ProdCountry> prodCountryList = new HashSet<>();
	
	@Autowired
	MovieService movieService;
	
	
	
	
	
	public Movie parseRow(Row row) throws JsonMappingException, JsonProcessingException {
		Movie movie = new Movie();
		movie.setBudget(row.getCellAsNumber(0).isPresent() ? row.getCellAsNumber(0).get().intValue() : 0);
		movie.setGenre(prepareGenre(row.getCellAsString(1).get()));
		movie.setHomepage(row.getCellText(2));
		movie.setId(row.getCellAsNumber(3).get().intValue());
		movie.setKeyword(prepareKeywords(row.getCellAsString(4).get()));
		movie.setOrigLang(row.getCellText(5));
		movie.setOrigTitle(row.getCellText(6));
		movie.setOverview(row.getCellText(7));
		movie.setPopularity(row.getCellAsNumber(8).isPresent() ? row.getCellAsNumber(8).get() : new BigDecimal(0));
		movie.setProdCount(prepareProductionCountry(row.getCellAsString(10).get()));
		movie.setProdComp(prepareProductionCompany(row.getCellAsString(9).get()));
		movie.setReleaseDate(row.getCellAsDate(11).isPresent() ? Date.from(row.getCellAsDate(11).get().atZone(ZoneId.systemDefault()).toInstant()) :new Date() );
		movie.setRevenue(row.getCellAsNumber(12).get().longValue());
		movie.setRuntime(row.getCellAsNumber(13).isPresent() ? row.getCellAsNumber(13).get().intValue() : 0);
		return movie;
		
		
		
	}
	
	public static List<Genre> prepareGenre(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<Genre> genres = ob.readValue(json, new TypeReference<List<Genre>>(){});
		return genres;

	}
	public static List<Keywords> prepareKeywords(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<Keywords> keywords = ob.readValue(json, new TypeReference<List<Keywords>>(){});
		return keywords;

	}
	
	public static List<ProdCompany> prepareProductionCompany(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<ProdCompany> prodCompanies = ob.readValue(json, new TypeReference<List<ProdCompany>>(){});
		return prodCompanies;

	}
	
	
	public static List<ProdCountry> prepareProductionCountry(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<ProdCountry> prodCountries = ob.readValue(json, new TypeReference<List<ProdCountry>>(){});
		return prodCountries;

	}
	
	
	
	

}
