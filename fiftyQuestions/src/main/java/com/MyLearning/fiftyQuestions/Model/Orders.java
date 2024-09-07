package com.MyLearning.fiftyQuestions.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;


@NamedEntityGraph(name="OrdersEagerFetch",attributeNodes = {
		@NamedAttributeNode("orderItems")
})
@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Version                                     its optimistic locking.... read about it to know more
//	private Integer version;			2 users try to book same seat in a bus, one gets the seat, other process will stop.
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinColumn(name = "users_id")
	private Users users;
	
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime orderDate;
	
	@PrePersist
	protected void onCreate() {
		if(orderDate == null) {
			orderDate = LocalDateTime.now();
		}
	}
	
	
	private BigDecimal totalAmount;
	
	@OneToMany(mappedBy ="orders",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonManagedReference
	private List<OrderItems> orderItems = new ArrayList<>();

	public Orders(Long id, Users users, LocalDateTime orderDate, BigDecimal totalAmount) {
		super();
		this.id = id;
		this.users = users;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	
	
	public Orders() {
		super();
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime  getOrderDate() {
		return orderDate;
	}

	public BigDecimal gettotalAmount() {
		return totalAmount;
	}
	

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}



	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}



	@Override
	public String toString() {
		return "Orders [id=" + id + ", users=" + users + ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + "]";
	}
	
	
}
