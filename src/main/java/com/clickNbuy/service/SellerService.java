package com.clickNbuy.service;

import java.security.Principal;

import com.clickNbuy.dto.ProductDto;
import com.clickNbuy.dto.ResponseDto;

import jakarta.validation.Valid;

public interface SellerService {

	ResponseDto saveProduct(@Valid ProductDto productDto, Principal principal);

	ResponseDto getProducts(Principal principal);

	ResponseDto deleteProduct(Long id, Principal principal);

}
