package com.dapperdrakes.dimo.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

import com.dapperdrakes.dimo.error.MovieNotFoundException;
import com.dapperdrakes.dimo.model.MovieDetailDTO;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.dapperdrakes.dimo.dao.MovieRepository;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.util.ExcelParser;
import com.dapperdrakes.dimo.model.GenericResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	public void createData(Movie movie) {
		movieRepository.save(movie);
	}

	public void create() throws IOException {
		parseExcel();

	}

	public void parseExcel() throws IOException {
		ExcelParser excelParser = new ExcelParser();
		Resource resource = new ClassPathResource("tmdb_5000.xlsx");

		try (InputStream is = resource.getInputStream(); ReadableWorkbook wb = new ReadableWorkbook(is)) {
			Sheet sheet = wb.getFirstSheet();
			try (Stream<Row> rows = sheet.openStream()) {
				rows.forEach(r -> {

					if (!r.getCellText(0).isEmpty()) {
						try {
							createData(excelParser.parseRow(r));
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}

	public Object getMovie() {
		GenericResponse genericResponse = new GenericResponse();
		genericResponse.setSuccess(true);
		genericResponse.setData(movieRepository.findAll());
		return genericResponse;
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

}
