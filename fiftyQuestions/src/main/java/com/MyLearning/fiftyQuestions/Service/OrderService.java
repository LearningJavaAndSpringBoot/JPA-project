package com.MyLearning.fiftyQuestions.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MyLearning.fiftyQuestions.Model.Orders;
import com.MyLearning.fiftyQuestions.Model.Users;
import com.MyLearning.fiftyQuestions.Repository.OrdersRepo;

import DTO.UsersDTO;
import jakarta.transaction.Transactional;


@Service
public class OrderService {
	@Autowired
	private OrdersRepo orepo;
	
	@Transactional
	public ResponseEntity<List<Orders>> findAllOrders(){
		return new ResponseEntity<List<Orders>>(orepo.findAll(),HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<List<Orders>> getAllOrdersBetweenASpecificDateRange(String start, String end){
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDateTime parserStart = LocalDate.parse(start, format).atTime(0, 0, 0);
		LocalDateTime parserEnd = LocalDate.parse(end,format).atTime(0, 0, 0);
		return new ResponseEntity<List<Orders>>(orepo.getAllOrdersBetweenASpecificDateRange(parserStart, parserEnd),HttpStatus.OK);
	}

	@Transactional
	public void deleteAnOrderAndTheCorrespondingOrderItems(Long id) {
		orepo.deleteById(id);
	}
	
	public List<Orders> findAll(){
		return orepo.findAll();
	}
	
	@Transactional
	public ResponseEntity<List<UsersDTO>> getAllUsersFromOrdersWhereOrderPriceIsGreaterThan(BigDecimal amount){
		
		List<Users> users = orepo.getAllUsersFromOrdersWhereOrderPriceIsGreaterThan(amount);
		List<UsersDTO> usersList = users.stream().map(this::getUsersDTOfromUsers).collect(Collectors.toList());
		return new ResponseEntity<List<UsersDTO>>(usersList,HttpStatus.OK);
	}
	
	private UsersDTO getUsersDTOfromUsers(Users user) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setId(user.getId());
		usersDTO.setUserName(user.getUserName());
		return usersDTO;
	}
}
