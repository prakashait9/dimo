package com.dapperdrakes.dimo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dapperdrakes.dimo.service.MovieService;

@RestController
public class MovieCreationController {

	@Autowired
	MovieService movieService;

	@GetMapping("create")
	public String create() throws IOException {
		movieService.create();
		return "Success";
	}

	@GetMapping("count")
	public Long count() throws IOException {
		return movieService.getCount();
	}

	@GetMapping("get")
	public Object get() throws IOException {
		return movieService.getMovie();

	}

}
