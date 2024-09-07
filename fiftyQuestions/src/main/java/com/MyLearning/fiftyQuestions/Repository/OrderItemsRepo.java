package com.MyLearning.fiftyQuestions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MyLearning.fiftyQuestions.Model.OrderItems;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItems, Long>{

}
