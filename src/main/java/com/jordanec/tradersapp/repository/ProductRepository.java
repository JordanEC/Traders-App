package com.jordanec.tradersapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordanec.tradersapp.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
