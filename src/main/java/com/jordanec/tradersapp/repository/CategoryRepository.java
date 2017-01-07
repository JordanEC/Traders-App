package com.jordanec.tradersapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordanec.tradersapp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
