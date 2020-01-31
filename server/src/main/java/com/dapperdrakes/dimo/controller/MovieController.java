package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.GenericResponse;
import com.dapperdrakes.dimo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class MovieController {

	@Autowired
	MovieService movieService;

	private static Logger log = LoggerFactory.getLogger(MovieController.class);

	@PostMapping("/upload")
	public GenericResponse singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		log.info("Handling File Upload");
		return movieService.create(file);
	}
	
	@GetMapping("/getbygenre")
	public Object getMoviesGroupedByGenre(@RequestParam("limit") Long limit) {
		return movieService.getMoviesGroupedByGenre(limit).getData();
	}


	@GetMapping("count")
	public Long count() throws IOException {
		return movieService.getCount();
	}

	@GetMapping("/trending")
	public GenericResponse trending(@RequestParam("limit") Long limit) throws IOException {
		return movieService.getMovie(limit);

	}

	@GetMapping("/getbyname")
	public Object get(@RequestParam("name") String name) throws IOException {
		return movieService.getSortedMovie(name);

	}

	

}
