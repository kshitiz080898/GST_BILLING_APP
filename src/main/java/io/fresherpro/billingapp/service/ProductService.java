package io.fresherpro.billingapp.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fresherpro.billingapp.model.Product;
import io.fresherpro.billingapp.repository.ProductRepository;


@Service
public class ProductService {
	
	private ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository= productRepository;
	}

	public Product save(Product product) {
		if (product.getProduct_code() != null && productRepository.existsById(product.getProduct_code())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return productRepository.save(product);
	}

	public Product update(Product product) {
		if (product.getProduct_code() != null && !productRepository.existsById(product.getProduct_code())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return productRepository.save(product);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findOne(Integer product_code) {
		return  productRepository.findById(product_code) ;
	}
	

}
