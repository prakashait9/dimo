package com.dapperdrakes.dimo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dapperdrakes.dimo.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
	
	@Query("{name : ?0}")
    public Movie findByNameQuery(String name);

}
