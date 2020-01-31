package com.dapperdrakes.dimo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.dapperdrakes.dimo.dao.MovieRepository;
import com.dapperdrakes.dimo.model.GenericResponse;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.model.MovieGroupByGenreDto;
import com.dapperdrakes.dimo.util.ExcelParser;
import com.dapperdrakes.dimo.util.ValidationUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.DistinctIterable;

import com.dapperdrakes.dimo.error.MovieNotFoundException;
import com.dapperdrakes.dimo.model.MovieDetailDTO;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MovieService {

	private static Logger log = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private RestTemplate restTemplate;

	public void createData(Movie movie) {
		movieRepository.save(movie);
	}

	public GenericResponse create(MultipartFile file) throws IOException {
		return parseExcel(file);
	}


	public GenericResponse getMoviesGroupedByGenre(Long limit) {
		DistinctIterable<String> genre = mongoTemplate.getCollection("Movies").distinct("genre.name",String.class);
	    PageRequest request = PageRequest.of(0, limit.intValue(), Sort.by(Sort.Direction.DESC, "popularity"));
	    List<MovieGroupByGenreDto> response = new ArrayList<MovieGroupByGenreDto>();
	    genre.iterator().forEachRemaining(str ->{
	    	MovieGroupByGenreDto movieGroupByGenreDto = new MovieGroupByGenreDto();
	    	movieGroupByGenreDto.setGenre(str);
	    	movieGroupByGenreDto.setMovie(movieRepository.findByGenre(str, request));
	    	response.add(movieGroupByGenreDto);
	    });
		return new GenericResponse(response);


	}








	private GenericResponse parseExcel(MultipartFile file) throws IOException {
		ExcelParser excelParser = new ExcelParser(restTemplate);
		ValidationUtility validationUtility = new ValidationUtility();
		if (!validationUtility.isSameExtension(file, "xlsx")) {
			return new GenericResponse(false, "Invalid file format");
		}
		try (InputStream is = file.getInputStream(); ReadableWorkbook wb = new ReadableWorkbook(is)) {
			Sheet sheet = wb.getFirstSheet();
			try (Stream<Row> rows = sheet.openStream()) {
				rows.forEach(r -> {
					try {
						createData(excelParser.parseRow(r));
					} catch (JsonProcessingException e) {
						log.error(e.getMessage());
					}
				});
			}
		}
		return new GenericResponse();
	}

	public GenericResponse getMovie(Long limit) {
		PageRequest request = PageRequest.of(0, limit.intValue(), Sort.by(Sort.Direction.DESC, "popularity"));
		Query query = new Query();
		query.with(request);
		query.fields().include("_id");
		query.fields().include("tagline");
		query.fields().include("title");
		query.fields().include("overview");
		query.fields().include("poster");
		query.fields().include("popularity");
		return new GenericResponse(mongoTemplate.find(query, Movie.class));

	}

	public Long getCount() {
		return movieRepository.count();
	}

	public List<Movie> getAllMovie() {
		return mongoTemplate.findAll(Movie.class);
	}

	public GenericResponse getMovieById(int id) throws MovieNotFoundException {
		Movie movie = movieRepository.getMovieDetails(id);
		if(movie == null){
			throw new MovieNotFoundException("Movie with id: " + id + " does not exist");
		}
		MovieDetailDTO movieDetailDTO = new MovieDetailDTO(movie);
		return new GenericResponse(movieDetailDTO);
	}



	public GenericResponse getSortedMovie(String name) {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setSuccess(true);
		genericResponse.setData(movieRepository.findByNameQuery(name, Sort.by(Direction.DESC, "popularity")));
		return genericResponse;
	}



}
