package com.MyLearning.fiftyQuestions.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyLearning.fiftyQuestions.Model.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long>{
	
	@Query("select p from Products p where p.price>:priceCheck")
	public List<Products> getAllProductsGreaterThanThisPrice(BigDecimal priceCheck);
	
	@Query("select sum(p.price*oi.quantity) from Products p left join p.orderItems oi where p.id=:id")
	public BigDecimal totalSalesOfASpecificProduct(Long id);
	
	@Query("select p from Products p where p.isDeleted=false and p.id=1")
	public Optional<Products> getTheProductWhoMustBeSoftDeleted();

	@Query("select p from Products p where p.isDeleted=true")
	public List<Products> getProductWhoGotSoftDeleted();
}
