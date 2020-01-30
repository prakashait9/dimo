package com.dapperdrakes.dimo.service;

import com.dapperdrakes.dimo.dao.MovieRepository;
import com.dapperdrakes.dimo.error.MovieNotFoundException;
import com.dapperdrakes.dimo.model.GenericResponse;
import com.dapperdrakes.dimo.model.Movie;
import com.dapperdrakes.dimo.model.MovieDetailDTO;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private MovieRepository movieRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetMovieById() {
        int id = 1;
        Movie movie = new Movie();
        GenericResponse expected = new GenericResponse(new MovieDetailDTO(movie));
        when(movieRepository.getMovieDetails(id)).thenReturn(movie);

        GenericResponse result = movieService.getMovieById(id);

        assertEquals(expected, result);

    }

    @Test(expected = MovieNotFoundException.class)
    public void testGetMovieForInvalidId() {
        int id = 1;
        when(movieRepository.getMovieDetails(id)).thenReturn(null);
        movieService.getMovieById(id);
    }


}