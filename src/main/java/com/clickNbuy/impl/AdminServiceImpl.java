package com.clickNbuy.impl;

import org.springframework.stereotype.Service;

import com.clickNbuy.dao.AdminDao;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.entity.Category;
import com.clickNbuy.exception.DataExitsException;
import com.clickNbuy.service.AdminService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
	
	AdminDao adminDao;

	@Override
	public ResponseDto addCategory(Category category) {
		if (adminDao.isCategoryUnique(category.getName())) {
			adminDao.save(category);
			return new ResponseDto("Category Added Success", category);
		} else
			throw new DataExitsException(category.getName() + " Already Present");
	}
	@Override
	public ResponseDto viewCategories() {
		return new ResponseDto("Found Success", adminDao.findAllCategory());
	}

	@Override
	public ResponseDto deleteCategory(Long id) {
		adminDao.deleteCategory(id);
		return new ResponseDto("Deleted Success", null);
	}

	@Override
	public ResponseDto updateCategory(Long id, Category req) {
		Category category = adminDao.findCategoryById(id);
		category.setName(req.getName());
		adminDao.save(category);

		return new ResponseDto("Category Updated Success", category);
	}

}
