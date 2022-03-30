package com.mark2.mglow.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark2.mglow.helper.ProductRepository;
import com.mark2.mglow.model.Response;
import com.mark2.mglow.model.ProductData;


@RestController
@CrossOrigin("*")
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping("mock/product/create")
	public ResponseEntity<?> createProduct(@RequestBody String product) {
		try 
		{
			ObjectMapper mapper = new ObjectMapper();
			ProductData productData = mapper.readValue(product.getBytes(), ProductData.class);
			productData.setProductId(""+new Date().getTime());
			
			if(productData.getProductName() != null && productData.getProductDetail() != null) {
				try {
					 this.productRepo.save(productData);
					 System.out.println("LOG: success");
					 return ResponseEntity.ok(new Response("success",1));
				}
				catch(Exception ex)
				{
					System.out.println("log: "+ex.getMessage());
					return ResponseEntity.ok(new Response(ex.getMessage(),0));
				}
			}
			else {
				System.out.println("LOG: product name of description is missing.");
				return ResponseEntity.ok(new Response("product name or description is missing!",0));
			}
		}
		catch(Exception ex) {
			System.out.println("LOG: "+ex.getMessage());
			return ResponseEntity.ok(new Response(ex.getMessage(),0));	
		}
		
	}
}
