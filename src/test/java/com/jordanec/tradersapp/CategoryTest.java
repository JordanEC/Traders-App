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
import com.jordanec.tradersapp.controller.CategoryController;
import com.jordanec.tradersapp.model.Product;
import com.jordanec.tradersapp.model.Category;
import com.jordanec.tradersapp.repository.CategoryRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class CategoryTest {
	@InjectMocks
	private CategoryController sc;

	@Autowired
	private CategoryRepository categoryRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	//@Test
	public void testCategoryCreate() {
		Category new_category = new Category();
		Category ret_new_category;
		new_category.setId(1l);
		new_category.setName("name");

		when(ret_new_category=categoryRepository.save(new_category)).thenReturn(new_category);
		
		Category category = sc.get(2L);

		verify(categoryRepository).findOne(2l);		

		assertThat(category, is(ret_new_category));
		System.out.println(category);	
	}
	
	@Test
	public void testCategoryList() {
		Collection<Category> categories;
		Collection<Product> products;
		
		categories = categoryRepository.findAll();
		for (Category category : categories) {
			System.out.println("\n<Category>");
			System.out.println("ID: "+ category.getId());
			System.out.println("Name: "+ category.getName());
			products = category.getProducts();
			System.out.println("<Products>:");
			
			for(Product product: products) {
				System.out.println("<Product>");
				System.out.println("ID: "+product.getId());
				System.out.println("Name: "+product.getName());
				System.out.println("Category: "+product.getCategory().getName());
			}
		}
	}
}
