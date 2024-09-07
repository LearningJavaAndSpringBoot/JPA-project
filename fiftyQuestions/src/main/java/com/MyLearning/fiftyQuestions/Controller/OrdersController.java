
package com.MyLearning.fiftyQuestions.Controller;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyLearning.fiftyQuestions.Model.Orders;
import com.MyLearning.fiftyQuestions.Model.Users;
import com.MyLearning.fiftyQuestions.Service.OrderService;

import DTO.UsersDTO;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrderService oservice;
	
	@GetMapping("/all")
	public ResponseEntity<List<Orders>> findAllOrders(){
		return oservice.findAllOrders();
	}
	
	@GetMapping("/{start}/{end}")
	public ResponseEntity<List<Orders>> getAllOrdersBetweenASpecificDateRange(@PathVariable(name = "start") String start, @PathVariable(name ="end") String end){
		return oservice.getAllOrdersBetweenASpecificDateRange(start, end);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAnOrderAndTheCorrespondingOrderItems(@PathVariable Long id) {
		oservice.deleteAnOrderAndTheCorrespondingOrderItems(id);
	}
	
	@GetMapping("/newall")
	public List<Orders> findAll(){
		return oservice.findAll();
	}
	
	@GetMapping("/all/{amount}")
	public ResponseEntity<List<UsersDTO>> getAllUsersFromOrdersWhereOrderPriceIsGreaterThan(@PathVariable BigDecimal amount){
		return oservice.getAllUsersFromOrdersWhereOrderPriceIsGreaterThan(amount);
	}
}
