package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	List<User> findByAge(@Param("email") String email);

}
