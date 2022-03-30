package com.mark2.mglow.helper;

import org.springframework.data.repository.CrudRepository;

import com.mark2.mglow.model.ProductData;


public interface ProductRepository extends CrudRepository<ProductData,String>{
	
}
