package com.clickNbuy.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Sort;

import com.clickNbuy.dao.SellerDao;
import com.clickNbuy.dao.UserDao;
import com.clickNbuy.dto.ProductDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.entity.Product;
import com.clickNbuy.entity.User;
import com.clickNbuy.exception.DataExitsException;
import com.clickNbuy.exception.DataNotFoundException;
import com.clickNbuy.service.SellerService;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SellerServiceImpl implements SellerService {
	
	SellerDao sellerDao;
	UserDao userDao;
	RestTemplate restTemplate;

	@Override
	public ResponseDto saveProduct(ProductDto dto, Principal principal) {
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
	public ResponseDto getProducts(Principal principal, int page, int size, String sortBy, boolean desc) {
		User user = userDao.findByEmail(principal.getName());
		Sort sort = desc ? Sort.by(sortBy).descending() : Sort.by(sortBy);
		Pageable pageable = PageRequest.of(page - 1, size, sort);
		List<Product> products = sellerDao.fetchProducts(user, pageable);
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
	@SuppressWarnings("unchecked")
	@Override
	public ResponseDto addProducts(Principal principal) {
		Map<String, List<Map<String, Object>>> map = restTemplate.getForObject("https://dummyjson.com/products",Map.class);
		List<Product> products = new ArrayList<Product>();
		for (Map<String, Object> productDto : map.get("products")) {
			String category = (String) productDto.get("category");
			String name = (String) productDto.get("title");
			String description = (String) productDto.get("description");
			String brand = (String) productDto.get("brand");
			String imageLink = ((List<String>) productDto.get("images")).get(0);
			Double price = ((Double) productDto.get("price")) * 87.87;
			Integer stock = (Integer) productDto.get("stock");

			if (sellerDao.isCategoryPresent(category)) {
				if (sellerDao.isProductUnique(name, brand, price)) {
					Product product = new Product(null, name, description, price, stock, imageLink,
							sellerDao.getCategory(category), brand, false, userDao.findByEmail(principal.getName()));
					products.add(product);
				} else {
					throw new DataExitsException("Product Already Exists " + name);
				}
			} else {
				throw new DataNotFoundException("No Category with name: " + category);
			}
		}
		return new ResponseDto("Product Added Success", sellerDao.saveAllProducts(products));
	}

	@Override
	public ResponseDto updateProduct(Long id, Product product, Principal principal) {
		Product existingProduct = sellerDao.findProductById(id);
		if (existingProduct.getUser().getEmail().equals(principal.getName())) {
			product.setId(id);
			product.setUser(existingProduct.getUser());
			sellerDao.saveProduct(product);
			System.out.println("Logged-in user: " + principal.getName());
			System.out.println("Product owner: " + existingProduct.getUser().getEmail());

		} else
			throw new AuthorizationDeniedException("You can not update this product");
		return new ResponseDto("Product Updated Success", product);
	}

	
	
}


