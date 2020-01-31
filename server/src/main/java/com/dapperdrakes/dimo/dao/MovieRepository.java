package com.dapperdrakes.dimo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.dapperdrakes.dimo.model.Movie;

@Repository
public interface MovieRepository extends MongoRepository<Movie, Long> {
	


	@Query("{origTitle:{'$regex' : ?0 , $options: 'i'}}")
    public List<Movie> findByNameQuery(String name, Sort sort);

	@Query(value = "{'genre.name' : ?0}",fields = "{_id:1,title:1,overview:1,tagline:1,popularity:1,poster:1}")
    public List<Movie> findByGenre(String name, Pageable pageable);

	@Query(value="{_id : ?0}",fields="{_id:1,origTitle:1,releaseDate:1,spoken_lang:1,runtime:1,genre:1,overview:1,poster:1}")
    public Movie getMovieDetails(int mid);

}
