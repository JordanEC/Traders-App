package com.jordanec.tradersapp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jordanec.tradersapp.model.Supplier;
import com.jordanec.tradersapp.repository.SupplierRepository;



@RestController
@RequestMapping("api/v2/")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @RequestMapping(value = "suppliers", method = RequestMethod.GET)
    public List<Supplier> list() {
        return supplierRepository.findAll();
    }

    @RequestMapping(value = "suppliers", method = RequestMethod.POST)
    public Supplier create(@RequestBody Supplier supplier) {
        return supplierRepository.saveAndFlush(supplier);
    }

    @RequestMapping(value = "suppliers/{id}", method = RequestMethod.GET)
    public Supplier get(@PathVariable Long id) {
        return supplierRepository.findOne(id);
    }

    @RequestMapping(value = "suppliers/{id}", method = RequestMethod.PUT)
    public Supplier update(@PathVariable Long id, @RequestBody Supplier supplier) {
        Supplier existingSupplier = supplierRepository.findOne(id);
        BeanUtils.copyProperties(supplier, existingSupplier);
        return supplierRepository.saveAndFlush(existingSupplier);
    }

    @RequestMapping(value = "suppliers/{id}", method = RequestMethod.DELETE)
    public Supplier delete(@PathVariable Long id) {
        Supplier existingSupplier = supplierRepository.findOne(id);
        supplierRepository.delete(existingSupplier);
        return existingSupplier;
    }
}
