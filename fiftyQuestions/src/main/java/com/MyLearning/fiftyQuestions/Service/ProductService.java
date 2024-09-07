package com.MyLearning.fiftyQuestions.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MyLearning.fiftyQuestions.Model.Products;
import com.MyLearning.fiftyQuestions.Repository.ProductsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductService {
	
	@Autowired
	private ProductsRepo prepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public ResponseEntity<Products> updateStockCount(Long id,Integer updateCount){
		
		Optional<Products> product = prepo.findById(id);
		if(product.isPresent()) {
			Products p = product.get();
			p.setStock(p.getStock() + updateCount);
			return new ResponseEntity<Products>(prepo.save(p),HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public ResponseEntity<List<Products>> getAllProductsGreaterThanThisPrice(BigDecimal priceCheck){
		return new ResponseEntity<List<Products>>(prepo.getAllProductsGreaterThanThisPrice(priceCheck),HttpStatus.OK);
	}
	
	@Transactional
	public BigDecimal totalSalesOfASpecificProduct(Long id) {
		return prepo.totalSalesOfASpecificProduct(id);
	}
	
	@Transactional
	public String updateAllProdcutsPriceByInputUsingBatchProcessing(BigDecimal priceIncrement){
		
		Long size = prepo.count();
		int batchSize = 3;
		for(int i=1;i<size;i++) {
			Products product = em.find(Products.class, i); //usually we dont hit DB for this... user or another service will procide list of products in that we just get the products
			product.setPrice(product.getPrice().add(priceIncrement));
			
			//batching
			if(i%batchSize==0 && i>0) {
				System.out.println("batch is getting updated");
				em.flush();
				em.clear();
			}
		}
		
		em.flush();
		em.clear();
		return "success";
	}
	
	@Transactional
	public String getTheProductWhoMustBeSoftDeleted(){
	
		Optional<Products> product = prepo.getTheProductWhoMustBeSoftDeleted();
		if(product.isPresent()) {
			Products p = product.get();
			p.setDeleted(true);
			prepo.save(p);
			return "done";
		}
		else {
			return "no product is present with your requirements";
		}
	}
	
	@Transactional
	public ResponseEntity<List<Products>> getProductWhoGotSoftDeleted(){
		List<Products> softDeletedProducts = prepo.getProductWhoGotSoftDeleted();
		return new ResponseEntity<List<Products>>(softDeletedProducts,HttpStatus.OK);
		
	}
}
