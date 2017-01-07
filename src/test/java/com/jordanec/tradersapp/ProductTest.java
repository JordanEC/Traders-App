package com.jordanec.tradersapp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jordanec.tradersapp.App;
import com.jordanec.tradersapp.controller.ProductController;
import com.jordanec.tradersapp.model.Product;
import com.jordanec.tradersapp.model.Supplier;
import com.jordanec.tradersapp.repository.ProductRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
//@SpringApplicationConfiguration(App.class)
public class ProductTest {
	@InjectMocks
	private ProductController productController;
	
	@Autowired
	private ProductRepository productRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
	@Test
	public void testProductCreate() {
		long lo = 9L;
		Product new_product = new Product();
		Product ret_new_product;
		new_product.setId(lo);
		new_product.setName("name");
		new_product.setDescription("description");
		new_product.setUnitPrice(25.0);
		new_product.setUnitsInStock(10);

		when(ret_new_product=productRepository.save(new_product)).thenReturn(new_product);
		
		Product product = productController.get(lo);

		verify(productRepository).findOne(lo);		

		assertThat(product, is(ret_new_product));
		System.out.println(product);
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
