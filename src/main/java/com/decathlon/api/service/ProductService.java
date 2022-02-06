package com.decathlon.api.service;

import java.util.List;

import com.decathlon.api.dto.ProductDto;
import com.decathlon.api.dto.UserDto;

public interface ProductService {
	ProductDto addProductService(ProductDto productDto);
	List<ProductDto> getProductListService(int page, int limit);
	public ProductDto getProductByProductId(long productId);
}
