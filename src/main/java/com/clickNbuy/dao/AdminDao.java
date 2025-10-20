package com.clickNbuy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.clickNbuy.entity.Category;
import com.clickNbuy.exception.DataNotFoundException;
import com.clickNbuy.repository.CategoryRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class AdminDao {

	CategoryRepository categoryRepository;
	
	public boolean isCategoryUnique(String name) {
		return !categoryRepository.existsByName(name);
	}

	public void save(Category category) {
		categoryRepository.save(category);
		
	}
	
	public List<Category> findAllCategory() {
		List<Category> list = categoryRepository.findAll();
		if (list.isEmpty())
			throw new DataNotFoundException("No Categories Present");
		else
			return list;
	}

	public void deleteCategory(Long id) {
		findCategoryById(id);
		categoryRepository.deleteById(id);
	}

	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category Not Found"));
	}

}
