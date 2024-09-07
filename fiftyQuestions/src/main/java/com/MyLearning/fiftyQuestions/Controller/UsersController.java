package com.MyLearning.fiftyQuestions.Controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyLearning.fiftyQuestions.Model.Users;
import com.MyLearning.fiftyQuestions.Service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UserService uservice;
	
	@GetMapping("/all")
	public ResponseEntity<List<Object[]>> allUsers(){
		return uservice.allUsers();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Users> findUserById(@PathVariable Long id){
		return uservice.findUserById(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Users> saveUser(@RequestBody Users uu) {
		return new ResponseEntity<Users>(uservice.saveUser(uu),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteIfNameIsNull(){
		return uservice.deleteIfNameIsNull();
	}
	
	@PatchMapping("update/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable(name = "id") Long id,
									  @RequestParam(name="userName",defaultValue = "notEntered") String uname,
									  @RequestParam(name="email",defaultValue = "notEnterred") String uemail){
		return uservice.updateUser(id, uname, uemail);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<Users>> usersPaginatedAndSorted(@RequestParam(name="size", defaultValue = "2") Integer size,
															   @RequestParam(name="page", defaultValue = "0") Integer page,
															   @RequestParam(name="sortby", defaultValue = "userName") String sortby,
															   @RequestParam(name="sortdirection", defaultValue = "asc") String sortdirection){
		return uservice.usersPaginatedAndSorted(size, page, sortby, sortdirection);
		
	}
	
	@GetMapping("/filter")
	public ResponseEntity<List<Users>> getAllUsersByNameAndEmailSpecification(
			@RequestParam String name, @RequestParam String email){
		return uservice.getAllUsersByNameAndEmailSpecification(name, email);
	}
}
