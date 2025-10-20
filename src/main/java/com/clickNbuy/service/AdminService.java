package com.clickNbuy.service;

import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.entity.Category;

public interface AdminService {

	ResponseDto addCategory(Category category);

	ResponseDto viewCategories();

	ResponseDto deleteCategory(Long id);

	ResponseDto updateCategory(Long id, Category category);
	

}
