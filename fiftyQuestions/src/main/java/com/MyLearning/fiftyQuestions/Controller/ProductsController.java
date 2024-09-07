package com.MyLearning.fiftyQuestions.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyLearning.fiftyQuestions.Model.Products;
import com.MyLearning.fiftyQuestions.Service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	ProductService pService;
	
	@PatchMapping("/{id}")
	public ResponseEntity<Products> updateStockCount(@PathVariable Long id, @RequestParam(name="p") Integer quantity ){
		return pService.updateStockCount(id, quantity);
	}

	@GetMapping("/price/{price}")
	public ResponseEntity<List<Products>> getAllProductsGreaterThanThisPrice(@PathVariable(name="price") BigDecimal price){
		return pService.getAllProductsGreaterThanThisPrice(price);
	}
	
	@GetMapping("/{id}")
	public BigDecimal totalSalesOfASpecificProduct(@PathVariable Long id) {
		return pService.totalSalesOfASpecificProduct(id);
	}
	
	@PutMapping("/updateall/{price}")
	public String updateAllProdcutsPriceByInputUsingBatchProcessing(@PathVariable BigDecimal price){
		return pService.updateAllProdcutsPriceByInputUsingBatchProcessing(price);
	}
	
	@DeleteMapping("/softdelete")
	public String getTheProductWhoMustBeSoftDeleted() {
		return pService.getTheProductWhoMustBeSoftDeleted();
	}
	
	@GetMapping("/soft")
	public ResponseEntity<List<Products>> getProductWhoGotSoftDeleted(){
		return pService.getProductWhoGotSoftDeleted();
	}
}
