package io.fresherpro.billingapp.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.fresherpro.billingapp.model.Product;
import io.fresherpro.billingapp.service.ProductService;



@RestController
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService productService;

	
	@RequestMapping(value = "product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@RequestMapping(value = "product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
		try {
			Product result = productService.save(product);
			return ResponseEntity.created(new URI("/api/product/" + result.getProduct_code())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Product>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "product", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws URISyntaxException {
		if (product.getProduct_code() == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		try {
			Product result = productService.update(product);

			return ResponseEntity.created(new URI("/api/product/" + result.getProduct_code())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
	}
	@RequestMapping(value = "/product/{product_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Product> findOne(@PathVariable Integer product_code)
	{
		return productService.findOne(product_code);
	}
	
}
