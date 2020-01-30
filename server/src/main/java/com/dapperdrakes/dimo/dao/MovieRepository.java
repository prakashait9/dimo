package com.dapperdrakes.dimo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dapperdrakes.dimo.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
	
	@Query("{name : ?0}")
    public Movie findByNameQuery(String name);
	
	@Query(value="{_id : ?0}",fields="{_id:1,origTitle:1,releaseDate:1,spoken_lang:1,runtime:1,genre:1,overview:1,poster:1}")
    public Movie getMovieDetails(int mid);

}
