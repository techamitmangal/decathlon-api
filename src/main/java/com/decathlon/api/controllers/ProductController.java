package com.decathlon.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.decathlon.api.dto.ProductDto;
import com.decathlon.api.model.requests.AddProductReqModel;
import com.decathlon.api.model.responses.AddProductResModel;
import com.decathlon.api.model.responses.ProductListResModel;
import com.decathlon.api.service.ProductService;

@RestController
@RequestMapping("/v1/api/")
public class ProductController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ProductService productService ;
	
	@PostMapping(path = "addproduct")
	public AddProductResModel addProductAction(@RequestBody AddProductReqModel addProductReqModel) {
		
		AddProductResModel addProductResModel = new AddProductResModel();
		
		try {
			if (addProductReqModel.getProductId() == 0L) {
				addProductResModel.setMessage("Please provide valid product id");
				return addProductResModel;
			}
			
			if (addProductReqModel.getSkuId() == 0L) {
				addProductResModel.setMessage("Please provide valid sku id");
				return addProductResModel;
			}
			
			if (addProductReqModel.getPrice() == 0) {
				addProductResModel.setMessage("Please provide price");
				return addProductResModel;
			}
			
			if (addProductReqModel.getCurrency() == null || addProductReqModel.getCurrency().isBlank()) {
				addProductReqModel.setCurrency("INR");
			}
			
			if (addProductReqModel.getTitle() == null || addProductReqModel.getTitle().isBlank()) {
				addProductReqModel.setTitle("");
			}
			
			if (addProductReqModel.getCategory() == null || addProductReqModel.getCategory().isBlank()) {
				addProductReqModel.setCategory("");
			}
			
			if (addProductReqModel.getImageUrl() == null || addProductReqModel.getImageUrl().isBlank()) {
				addProductReqModel.setImageUrl("");
			}
			
			if (addProductReqModel.getDiscountPrice() == 0) {
				addProductReqModel.setDiscountPrice(addProductReqModel.getPrice());
			}
			
			if(addProductReqModel.getIsActive() == null) {
				addProductReqModel.setIsActive(false);
			}
			
			if(addProductReqModel.getIsBuyable() == null) {
				addProductReqModel.setIsBuyable(false);
			}
			
			if(addProductReqModel.getIsShippable() == null) {
				addProductReqModel.setIsShippable(false);
			}
			
			if(addProductReqModel.getIsFeaturedNew() == null) {
				addProductReqModel.setIsFeaturedNew(false);
			}
			
			if(addProductReqModel.getInPromotion() == null) {
				addProductReqModel.setInPromotion(false);
			}
			
			if(addProductReqModel.getIsOos() == null) {
				addProductReqModel.setIsOos(false);
			}
			
			try {
				if (addProductReqModel.getWeight() == 0) {
					addProductReqModel.setWeight(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setWeight(0);
			}
			
			try {
				if (addProductReqModel.getLength() == 0) {
					addProductReqModel.setLength(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setLength(0);
			}
			
			try {
				if (addProductReqModel.getWidth() == 0) {
					addProductReqModel.setWidth(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setWidth(0);
			}
			
			try {
				if (addProductReqModel.getHeight() == 0) {
					addProductReqModel.setHeight(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setHeight(0);
			}
			
			try {
				if (addProductReqModel.getNumberSold() == 0) {
					addProductReqModel.setNumberSold(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setNumberSold(0);
			}
			
			try {
				if (addProductReqModel.getNumberRemaining() == 0) {
					addProductReqModel.setNumberRemaining(0);
				}	
			} catch (Exception e) {
				addProductReqModel.setNumberRemaining(0);
			}
			
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(addProductReqModel, productDto);
			
			ProductDto productDtoRes = productService.addProductService(productDto);
			if (productDtoRes!=null) {
				addProductResModel.setStatusCode(200);
				addProductResModel.setSuccess(true);
				addProductResModel.setMessage("Product added successfully");
			}
			return addProductResModel;
		} catch (Exception e) {
			addProductResModel.setMessage(e.getMessage());
			return addProductResModel;
		}
	}
	
	@Cacheable(value = "products", key = "#productId")
	@GetMapping(path="getproductbyid")
	public ProductDto GetUsers(@RequestParam(value="productId", defaultValue="1") long productId) {
		LOG.info("Getting product with ID {}.", productId);
		ProductDto productDto = new ProductDto() ;
		productDto = productService.getProductByProductId(productId);
		return productDto;
	}
	
	@GetMapping(path="productlist")
	public ProductListResModel GetProducts(@RequestParam(value="page", defaultValue="0") int page, 
			@RequestParam(value="limit", defaultValue="10") int limit) {
		
		ProductListResModel productListResModel = new ProductListResModel ();
		List<ProductDto> productDtoList = productService.getProductListService(page, limit);
		productListResModel.setProducts(productDtoList);
		productListResModel.setStatusCode(200);
		productListResModel.setSuccess(true);
		return productListResModel;
	}
	
}
