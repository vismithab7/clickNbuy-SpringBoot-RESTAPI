package com.clickNbuy.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;

import com.clickNbuy.dao.SellerDao;
import com.clickNbuy.dao.UserDao;
import com.clickNbuy.dto.ProductDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.entity.Product;
import com.clickNbuy.entity.User;
import com.clickNbuy.exception.DataExitsException;
import com.clickNbuy.exception.DataNotFoundException;
import com.clickNbuy.service.SellerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {
	
	SellerDao sellerDao;
	UserDao userDao;

	@Override
	public ResponseDto saveProduct(@Valid ProductDto dto, Principal principal) {
		if (sellerDao.isCategoryPresent(dto.getCategory())) {
			if (sellerDao.isProductUnique(dto.getName(), dto.getBrand(), dto.getPrice())) {
				Product product = new Product(null, dto.getName(), dto.getDescription(), dto.getPrice(), dto.getStock(),
						dto.getImageLink(), sellerDao.getCategory(dto.getCategory()), dto.getBrand(), false,
						userDao.findByEmail(principal.getName()));
				sellerDao.saveProduct(product);
				return new ResponseDto("Product Added Success", product);
			} else {
				throw new DataExitsException("Product Already Exists");
			}
		} else {
			throw new DataNotFoundException("No Category with name: " + dto.getCategory());
		}
	}

	@Override
	public ResponseDto getProducts(Principal principal) {
		User user = userDao.findByEmail(principal.getName());
		List<Product> products = sellerDao.fetchProducts(user);
		return new ResponseDto("Products Found", products);
	}

	@Override
	public ResponseDto deleteProduct(Long id, Principal principal) {
		Product product = sellerDao.findProductById(id);
		if (product.getUser().getEmail().equals(principal.getName()))
			sellerDao.deleteProduct(id);
		else
			throw new AuthorizationDeniedException("You can not deleted this product");
		return new ResponseDto("Product Deleted Success", product);
	}
	}


