package com.dapperdrakes.dimo.controller;

import com.dapperdrakes.dimo.model.GenericResponse;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.model.MovieGroupByGenreDto;
import com.dapperdrakes.dimo.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<MovieGroupByGenreDto> movies = new ArrayList<>();
        MovieGroupByGenreDto movieGroupByGenreDto = new MovieGroupByGenreDto();
        movieGroupByGenreDto.setGenre("Trending");
        movieGroupByGenreDto.setMovie((List<Movie>) movieService.getMovie(limit).getData());
        movies.add(movieGroupByGenreDto);
        movies.addAll((List<MovieGroupByGenreDto>) movieService.getMoviesGroupedByGenre(limit).getData());

        return movies ;
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
