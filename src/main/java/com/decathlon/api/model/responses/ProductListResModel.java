package com.decathlon.api.model.responses;

import java.util.List;

import com.decathlon.api.dto.ProductDto;

public class ProductListResModel {
	int statusCode ;
	boolean success ;
	String message ;
	List<ProductDto> products ;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
}
