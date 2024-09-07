package com.MyLearning.fiftyQuestions.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.MyLearning.fiftyQuestions.Model.Users;

import jakarta.transaction.Transactional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users>{
	@Query("select u.userName,u.email from Users u")
	public List<Object[]> allUsers();

	@Query("select u from Users u where u.id=:id")
	public Optional<Users> findUserById(Long id);
	
	@Transactional
	@Modifying
	@Query("delete from Users u where u.userName is null")
	public void deleteIfNameIsNull();
}
