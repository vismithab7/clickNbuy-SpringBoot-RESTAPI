package com.clickNbuy.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.clickNbuy.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	boolean existsByName(String name);

	

	

	

	

	
}
