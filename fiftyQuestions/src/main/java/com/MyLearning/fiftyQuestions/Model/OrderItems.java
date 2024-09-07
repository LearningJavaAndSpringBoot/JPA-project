package com.MyLearning.fiftyQuestions.Model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="orders_id")
	@JsonBackReference
	private Orders orders;
	
	@ManyToOne
	@JoinColumn(name="products_id")
	@JsonBackReference
	private Products products;
	
	private Integer quantity;
	
	private BigDecimal price;

	public OrderItems(Long id, Integer quantity, BigDecimal price) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderItems() {
		super();
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public Orders getOrders() {
		return orders;
	}

	public Products getProducts() {
		return products;
	}

	@Override
	public String toString() {
		return "OrderItems [id=" + id + ", orders=" + orders + ", products=" + products + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	
}
