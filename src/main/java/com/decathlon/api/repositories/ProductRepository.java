package com.decathlon.api.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.decathlon.api.entities.ProductEntity;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
	ProductEntity findByProductId(long productId);
}