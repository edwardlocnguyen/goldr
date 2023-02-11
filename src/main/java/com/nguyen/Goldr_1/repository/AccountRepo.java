package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.User;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {

	@Query("SELECT a FROM Account a WHERE a.name = :name")
	List<Account> findByName(@Param("name") String name);

}
