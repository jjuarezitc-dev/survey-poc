package com.example.application.data.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.data.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String>{
	@Modifying
	@Query("update User u set u.completed = true where u.username = :username")
	void setAsCompleted(@Param("username") String username);
	
	@Query("select u.completed from User u where u.username = :username")
	boolean isSurveyCompletedByUsername(@Param("username") String username);
}
