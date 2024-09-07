package com.MyLearning.fiftyQuestions.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.MyLearning.fiftyQuestions.Model.Users;
import com.MyLearning.fiftyQuestions.Repository.UsersRepo;

import Specification.UserSpecification;
import jakarta.transaction.Transactional;


@Service
public class UserService {

	@Autowired
	private UsersRepo urepo;
	
	@Transactional
	public ResponseEntity<List<Object[]>> allUsers(){
		List<Object[]> all = urepo.allUsers();
		return new ResponseEntity<List<Object[]>>(all,HttpStatus.OK);
	}
	
	@Transactional
	public ResponseEntity<Users> findUserById(Long id){
		Optional<Users> u = urepo.findUserById(id);
		return u.map(x -> ResponseEntity.ok(x)).orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@Transactional
	public Users saveUser(Users user) {
		return urepo.save(user);
	}
	
	@Transactional
	public ResponseEntity<String> deleteIfNameIsNull(){
		urepo.deleteIfNameIsNull();
		return new ResponseEntity<String>("deletion done",HttpStatus.OK);
		
	}
	
	@Transactional
	public ResponseEntity<Users> updateUser(Long id, String name, String email){
		Optional<Users> userById = urepo.findUserById(id);
		if(userById.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		userById.get().setUserName(name);
		userById.get().setEmail(email);
		
		urepo.save(userById.get());
		return ResponseEntity.ok(userById.get());
		//return userById.map(u -> ResponseEntity.ok(u)).orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@Transactional
	public ResponseEntity<Page<Users>> usersPaginatedAndSorted(Integer size, Integer page, String sortby, String sortdirection){
		Sort sort =	sortdirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortby).ascending() : Sort.by(sortby).descending();
		PageRequest pagesData = PageRequest.of(page, size, sort);
		return new ResponseEntity<Page<Users>>(urepo.findAll(pagesData),HttpStatus.OK);
	}
	
	public ResponseEntity<List<Users>> getAllUsersByNameAndEmailSpecification(String name, String email){
		Specification<Users> spec = Specification
									.where(UserSpecification.hasName(name))
									.and(UserSpecification.hasEmail(email));
						
		return new ResponseEntity<List<Users>>(urepo.findAll(spec),HttpStatus.OK);
	}
}
	
