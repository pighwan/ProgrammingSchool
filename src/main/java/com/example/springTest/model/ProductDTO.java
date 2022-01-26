package com.example.springTest.model;

public class ProductDTO {
	private String name;
	private double price;
	
	public ProductDTO() {
	} // 파라미터가 없는 기본(디폴트) 생성자

	public ProductDTO(String name, double price) {
		this.name = name;
		this.price = price;
	} // 파라미터가 2개 있는 생성자

	// getter/setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	// toString
	@Override
	public String toString() {
		return "ProductDTO [name=" + name + ", price=" + price + "]";
	}
	
	
}
