package com.decathlon.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.decathlon.api.dto.ProductDto;
import com.decathlon.api.entities.ProductEntity;
import com.decathlon.api.repositories.ProductRepository;
import com.decathlon.api.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository ;
	
	@Override
	public ProductDto addProductService(ProductDto productDto) {
		
		if (productRepository.findByProductId(productDto.getProductId()) != null) {
			throw new RuntimeException("Product Id already exists");
		}
		
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(productDto, productEntity);
		
		ProductEntity savedProductEntity = productRepository.save(productEntity);
		ProductDto returnValue = new ProductDto();
		BeanUtils.copyProperties(savedProductEntity, returnValue);
		
		return returnValue;
	}
	
	@Override
	public ProductDto getProductByProductId(long productId) {
		// TODO Auto-generated method stub
		ProductDto productDto = new ProductDto();
		ProductEntity productEntity = productRepository.findByProductId(productId);
		BeanUtils.copyProperties(productEntity, productDto);		
		return productDto;
	}
	
	@Override
	public List<ProductDto> getProductListService(int page, int limit) {
		List<ProductDto> productDtoList = new ArrayList<ProductDto> ();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<ProductEntity> productPage = productRepository.findAll(pageableRequest);
		for (ProductEntity productEntity : productPage) {
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(productEntity, productDto);
			productDtoList.add(productDto);
		}
		return productDtoList;
	}

}
