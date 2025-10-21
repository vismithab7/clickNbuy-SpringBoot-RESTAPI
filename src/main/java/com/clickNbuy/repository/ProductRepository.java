package com.clickNbuy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clickNbuy.entity.Product;
import com.clickNbuy.entity.User;

public interface ProductRepository extends JpaRepository<Product, Long>{
	boolean existsByNameAndBrandAndPrice(String name, String brand, Double price);

	List<Product> findByUser(User user);

}
