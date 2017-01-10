package com.jordanec.tradersapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.jordanec.tradersapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}