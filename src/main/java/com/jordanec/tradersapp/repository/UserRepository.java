package com.jordanec.tradersapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.jordanec.tradersapp.model.User;

//@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
}