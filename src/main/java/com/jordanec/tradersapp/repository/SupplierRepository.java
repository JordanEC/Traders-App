package com.jordanec.tradersapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jordanec.tradersapp.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
