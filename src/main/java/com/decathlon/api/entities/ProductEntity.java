package com.decathlon.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="products")
public class ProductEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2607234991205007618L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false)
	private long productId;
	
	@Column(nullable=false)
	private long skuId;
	
	@Column(nullable=false, length=100)
	private String title;
	
	@Column(nullable=false, length=10)
	private String currency;
	
	@Column(nullable=false)
	private int price;
	
	@Column(nullable=false)
	private int discountPrice;
	
	@Column(nullable=true, length=100)
	private String category;
	
	@Column(nullable=true)
	private String imageUrl;
	
	
	private String rating;
	
	@Column(nullable=false)
	private Boolean isActive;
	
	private Boolean isBuyable;
	private Boolean isShippable;
	private Boolean isFeaturedNew;
	private Boolean inPromotion;
	private Boolean isOos;
	private int weight;
	private int length;
	private int width;
	private int height;
	private int numberRemaining;
	private int numberSold;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getSkuId() {
		return skuId;
	}
	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsBuyable() {
		return isBuyable;
	}
	public void setIsBuyable(Boolean isBuyable) {
		this.isBuyable = isBuyable;
	}
	public Boolean getIsShippable() {
		return isShippable;
	}
	public void setIsShippable(Boolean isShippable) {
		this.isShippable = isShippable;
	}
	public Boolean getIsFeaturedNew() {
		return isFeaturedNew;
	}
	public void setIsFeaturedNew(Boolean isFeaturedNew) {
		this.isFeaturedNew = isFeaturedNew;
	}
	public Boolean getInPromotion() {
		return inPromotion;
	}
	public void setInPromotion(Boolean inPromotion) {
		this.inPromotion = inPromotion;
	}
	public Boolean getIsOos() {
		return isOos;
	}
	public void setIsOos(Boolean isOos) {
		this.isOos = isOos;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getNumberRemaining() {
		return numberRemaining;
	}
	public void setNumberRemaining(int numberRemaining) {
		this.numberRemaining = numberRemaining;
	}
	public int getNumberSold() {
		return numberSold;
	}
	public void setNumberSold(int numberSold) {
		this.numberSold = numberSold;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
