package com.MyLearning.fiftyQuestions.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyLearning.fiftyQuestions.Model.Orders;
import com.MyLearning.fiftyQuestions.Model.Users;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

	@Query("select o from Orders o where o.orderDate between :startDate and :endDate")
	public List<Orders> getAllOrdersBetweenASpecificDateRange(LocalDateTime startDate, LocalDateTime endDate);
	
	@EntityGraph(value="OrdersEagerFetch",type = EntityGraphType.FETCH)
	List<Orders> findAll();
	
	@Query("select distinct o.users from Orders o where o.totalAmount > :amount")
	public List<Users> getAllUsersFromOrdersWhereOrderPriceIsGreaterThan(BigDecimal amount);
}
