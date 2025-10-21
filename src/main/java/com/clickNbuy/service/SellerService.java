package com.clickNbuy.service;

import java.security.Principal;

import com.clickNbuy.dto.ProductDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.entity.Product;

import jakarta.validation.Valid;

public interface SellerService {

	ResponseDto saveProduct(@Valid ProductDto productDto, Principal principal);

	ResponseDto getProducts(Principal principal, int page, int size, String sort, boolean desc);

	ResponseDto deleteProduct(Long id, Principal principal);
	
	ResponseDto addProducts(Principal principal);

	ResponseDto updateProduct(Long id, Product product, Principal principal);

}
