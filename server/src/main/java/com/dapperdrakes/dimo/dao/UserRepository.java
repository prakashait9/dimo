package com.dapperdrakes.dimo.dao;


public interface UserRepository {
    void findByEmail(String email);

}


/*
public interface UserRepository extends JpaRepository<User, Long> {
    void findByEmail(String email);

    @Override
    void delete(User user);
}*/
