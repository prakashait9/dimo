package com.dapperdrakes.dimo.dao;


import com.dapperdrakes.dimo.dao.model.DiMoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRepository extends JpaRepository<DiMoUser, Long> {

    @Query("from User u where u.email=:email")
    DiMoUser findByEmail(@PathVariable("email") String email);

}
