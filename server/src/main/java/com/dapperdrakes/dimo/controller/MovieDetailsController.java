package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.GenericResponse;
import com.dapperdrakes.dimo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieDetailsController {

    @Autowired
    MovieService movieService;

    @GetMapping("/api/movies/{id}")
    public GenericResponse getMovieById(@PathVariable int id){
        return movieService.getMovieById(id);
    }




}

