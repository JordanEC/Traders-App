package com.jordanec.tradersapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.jordanec.tradersapp.App;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jordanec.tradersapp.controller.SupplierController;
import com.jordanec.tradersapp.model.Product;
import com.jordanec.tradersapp.model.Supplier;
import com.jordanec.tradersapp.repository.SupplierRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class SupplierTest {
	@InjectMocks
	private SupplierController sc;

	@Autowired
	private SupplierRepository supplierRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	//@Test
	public void testSupplierCreate() {
		Supplier new_supplier = new Supplier();
		Supplier ret_new_supplier;
		new_supplier.setId(1l);
		new_supplier.setName("name");
		new_supplier.setAddress("address");
		new_supplier.setCompany("company");
		new_supplier.setPhone(88997744);

		when(ret_new_supplier=supplierRepository.save(new_supplier)).thenReturn(new_supplier);
		Supplier wreck = sc.get(2L);
		
		verify(supplierRepository).findOne(2l);
		
		assertThat(wreck, is(ret_new_supplier));
		System.out.println(wreck);
	}
	
	//@Test
	public void testSupplierList() {
		Collection<Supplier> suppliers;
		Collection<Product> products;
		
		suppliers = supplierRepository.findAll();
		
		for (Supplier supplier : suppliers) {
			System.out.println("\n<Supplier>");
			System.out.println("ID: "+ supplier.getId());
			System.out.println("Name: "+ supplier.getName());
			System.out.println("Company: "+ supplier.getCompany());
			System.out.println("Phone: "+ supplier.getPhone());
			products = supplier.getProducts();
			System.out.println("<Products>:");
			
			for(Product product: products) {
				System.out.println("<Product>");
				System.out.println("ID: "+product.getId());
				System.out.println("Name: "+product.getName());
				System.out.println("Category: "+product.getCategory().getName());
			}
		}
	}
	
	@Test
	public void testSupplierUpdate() {
		Supplier supplier = supplierRepository.findOne(1l);
		supplier.setName(supplier.getName()+"_UPDATED");
		supplierRepository.save(supplier);
	}
}