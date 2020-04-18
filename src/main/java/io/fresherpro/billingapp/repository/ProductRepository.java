package io.fresherpro.billingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.fresherpro.billingapp.model.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
