package com.mark2.mglow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productdata")
public class ProductData {
	
	@Id
	@Column(name="productid")
	String productId;
	
	@Column(name="productname")
	String productName;
	
	@Column(name="productdetail")
	String productDetail;
	
	@Column(name="price")
	String price;
	
	@Column(name="quantity")
	int qty;
	
	@Column(name="activation")
	boolean activation;
	
	public ProductData() {
	}
	
	public ProductData(String productId, String productName, String productDetail, String price, int qty,
			boolean activation) {

		this.productId = productId;
		this.productName = productName;
		this.productDetail = productDetail;
		this.price = price;
		this.qty = qty;
		this.activation = activation;
	}	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public boolean isActivation() {
		return activation;
	}
	public void setActivation(boolean activation) {
		this.activation = activation;
	}
	@Override
	public String toString() {
		return "ProductData [productId=" + productId + ", productName=" + productName + ", productDetail="
				+ productDetail + ", price=" + price + ", qty=" + qty + ", activation=" + activation + "]";
	}
	
}
