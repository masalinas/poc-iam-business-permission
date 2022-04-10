package io.oferto.pocolivebusinesspermission.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.oferto.pocolivebusinesspermission.domain.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	Logger log = LoggerFactory.getLogger(ProductController.class);
			
	private List<Product> products = new ArrayList<Product>(
			Arrays.asList(new Product("001", "Apple", 5, true),
						  new Product("002", "Banana", 4.3f, false),
						  new Product("003", "Orange", 3.2f, true)));
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> findAll() throws Exception {
        log.info("Executing Get All Products");
                    	
    	return products;
    }
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public Product findByCode(@PathVariable("code") String code) throws Exception {
        log.info("Executing Get Product by Code");
              
        Product product = products.stream()
        	.filter(entity -> code.equals(entity.getCode()))
        	.findAny()
        	.orElse(null);
        
    	return product;
    }
		
	@RequestMapping(value = "", method = RequestMethod.POST)
    public List<Product> save(@RequestBody Product product) throws Exception {
        log.info("Executing Create Product");
              
        products.add(product);
        
    	return products;
    }
	
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable String code) throws Exception {
        log.info("Executing Delete Product");
              
        Product product = products.stream()
            	.filter(entity -> code.equals(entity.getCode()))
            	.findAny()
            	.orElse(null);
        
        if (product != null)
        	return products.remove(product);
        
    	return false;
    }	
}
