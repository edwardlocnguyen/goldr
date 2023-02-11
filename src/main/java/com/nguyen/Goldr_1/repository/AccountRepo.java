package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	@Query("SELECT a FROM Account a WHERE a.user.id = :userId")
	List<Account> findByUserId(@Param("userId") Integer userId);

	@Query("SELECT a FROM Account a WHERE a.id = :id AND a.user.id = :userId")
	Optional<Account> findByIdAndUserId(@Param("userId") Integer userId, @Param("id") Integer id);
}
