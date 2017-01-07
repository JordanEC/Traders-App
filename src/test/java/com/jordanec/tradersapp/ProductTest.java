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
import com.jordanec.tradersapp.controller.ProductController;
import com.jordanec.tradersapp.model.Product;
import com.jordanec.tradersapp.model.Supplier;
import com.jordanec.tradersapp.repository.ProductRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ProductTest {
	@InjectMocks
	private ProductController sc;

	@Autowired
	private ProductRepository productRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	//@Test
	public void testProductCreate() {
		Product new_product = new Product();
		Product ret_new_product;
		new_product.setId(1l);
		new_product.setName("name");
		new_product.setDescription("description");
		new_product.setUnitPrice(25.0);
		new_product.setUnitsInStock(10);

		when(ret_new_product=productRepository.save(new_product)).thenReturn(new_product);
		
		Product wreck = sc.get(2L);

		verify(productRepository).findOne(2l);		

		assertThat(wreck, is(ret_new_product));
		System.out.println(wreck);
	}
	
	@Test
	public void testProductList() {
		Collection<Product> products;
		Collection<Supplier> suppliers;
		
		products = productRepository.findAll();
		for (Product product : products) {
			System.out.println("\n<Product>");
			System.out.println("ID: "+ product.getId());
			System.out.println("Name: "+ product.getName());
			System.out.println("UnitPrice: "+ product.getUnitPrice());
			System.out.println("UnitsInStock: "+ product.getUnitsInStock());
			System.out.println("Category: "+ product.getCategory());
			
			suppliers = product.getSuppliers();
			System.out.println("<Suppliers>:");
			
			for(Supplier supplier: suppliers) {
				System.out.println("<Supplier>");
				System.out.println("ID: "+supplier.getId());
				System.out.println("Name: "+supplier.getName());
			}
		}
	}
}
