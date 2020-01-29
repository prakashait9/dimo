package com.dapperdrakes.dimo.dao;


import com.dapperdrakes.dimo.model.DiMoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface DimoUserRepository extends MongoRepository<DiMoUser, Long> {
    DiMoUser findByEmail(@PathVariable("email") String email);
}
