package com.dapperdrakes.dimo.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dapperdrakes.dimo.model.Genre;
import com.dapperdrakes.dimo.model.Keywords;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.model.Poster;
import com.dapperdrakes.dimo.model.ProdCompany;
import com.dapperdrakes.dimo.model.ProdCountry;
import com.dapperdrakes.dimo.model.SpokenLang;
import com.dapperdrakes.dimo.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ExcelParser {

	Map<String, Integer> movieApiKeys = new HashedMap<>();
	private RestTemplate restTemplate;

	@Autowired
	MovieService movieService;

	public ExcelParser(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
		prepareAPIKeysMap();
	}

	@SuppressWarnings("unchecked")
	public Movie parseRow(Row row) throws JsonMappingException, JsonProcessingException {
		Movie movie = new Movie();
		movie.setBudget(row.getCellAsNumber(0).isPresent() ? row.getCellAsNumber(0).get().intValue() : 0);
		movie.setGenre((List<Genre>) convertJsonToObject(row.getCellAsString(1).get()));
		movie.setHomepage(row.getCellText(2));
		movie.setId(row.getCellAsNumber(3).get().intValue());
		movie.setKeyword((List<Keywords>) convertJsonToObject(row.getCellAsString(4).get()));
		movie.setOrigLang(row.getCellText(5));
		movie.setOrigTitle(row.getCellText(6));
		movie.setOverview(row.getCellText(7));
		movie.setPopularity(
				row.getCellAsNumber(8).isPresent() ? row.getCellAsNumber(8).get().doubleValue() : new Double(0));
		movie.setProdCount((List<ProdCountry>) convertJsonToObject(row.getCellAsString(10).get()));
		movie.setProdComp((List<ProdCompany>) convertJsonToObject(row.getCellAsString(9).get()));
		movie.setReleaseDate(row.getCellAsDate(11).isPresent()
				? Date.from(row.getCellAsDate(11).get().atZone(ZoneId.systemDefault()).toInstant())
				: new Date());
		movie.setRevenue(row.getCellAsNumber(12).get().longValue());
		movie.setRuntime(row.getCellAsNumber(13).isPresent() ? row.getCellAsNumber(13).get().intValue() : 0);
		movie.setSpoken_lang((List<SpokenLang>) convertJsonToObject(row.getCellAsString(14).get()));
		movie.setPoster(getMoviePoster(movie));
		movie.setStatus(row.getCellText(15));
		movie.setTagline(row.getCellText(16));
		movie.setTitle(row.getCellText(17));
		movie.setVoteAverage(
				row.getCellAsNumber(18).isPresent() ? row.getCellAsNumber(18).get().doubleValue() : new Double(0));
		movie.setVoteCount(row.getCellAsNumber(19).isPresent() ? row.getCellAsNumber(19).get().intValue() : 0);
		return movie;

	}

	private void prepareAPIKeysMap() {
		movieApiKeys.put("88e8ca0f", 0);
		movieApiKeys.put("70a5600c", 0);
		movieApiKeys.put("b690e515", 0);
		movieApiKeys.put("2a090a4e", 0);
		movieApiKeys.put("fb4be3e7", 0);
		movieApiKeys.put("8f29483b", 0);

	}

	private String getMoviePoster(Movie movie) {
		for (Map.Entry<String, Integer> entry : movieApiKeys.entrySet()) {
			if (entry.getValue() < 800) {
				Poster poster = new Poster();
				try {
					poster = restTemplate.getForObject(
							new URI("http://www.omdbapi.com/?apikey=" + entry.getKey() + "&t=" + URLEncoder.encode(movie.getOrigTitle(), "UTF-8")),
							Poster.class);
				} catch (RestClientException | URISyntaxException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				movieApiKeys.put(entry.getKey(), entry.getValue()+1);
				return poster.getPoster();
			}
		}
		return "";

	}

	private static List<?> convertJsonToObject(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<?> objectList = ob.readValue(json, new TypeReference<List<?>>() {
		});
		return objectList;

	}

	private static List<Genre> prepareGenre(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<Genre> genres = ob.readValue(json, new TypeReference<List<Genre>>() {
		});
		return genres;

	}

	private static List<Keywords> prepareKeywords(String json) throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<Keywords> keywords = ob.readValue(json, new TypeReference<List<Keywords>>() {
		});
		return keywords;

	}

	private static List<ProdCompany> prepareProductionCompany(String json)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<ProdCompany> prodCompanies = ob.readValue(json, new TypeReference<List<ProdCompany>>() {
		});
		return prodCompanies;

	}

	private static List<ProdCountry> prepareProductionCountry(String json)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		List<ProdCountry> prodCountries = ob.readValue(json, new TypeReference<List<ProdCountry>>() {
		});
		return prodCountries;

	}

}
