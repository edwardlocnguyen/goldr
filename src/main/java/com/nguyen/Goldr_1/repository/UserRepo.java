package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.User;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	@Query(value = "SELECT a.id, a.name AS account_name, asset.name AS asset_name, SUM(t.amount) AS total_amount "
			+ "FROM account a " + "INNER JOIN Txn t ON a.id = t.account_id "
			+ "INNER JOIN (SELECT account_id, MAX(date) AS max_date FROM Txn GROUP BY account_id) t1 "
			+ "ON a.id = t1.account_id AND t.date = t1.max_date " + "INNER JOIN Asset asset ON t.asset_id = asset.id "
			+ "WHERE a.user_id = :userId " + "GROUP BY a.id, a.name, asset.name", nativeQuery = true)
	List<Object[]> findAccountsAmountsByUserId(@Param("userId") Integer userId);

//	@Query(value = "SELECT a.id, a.name, SUM(t.amount) as total_amount FROM account a "
//			+ "INNER JOIN Txn t ON a.id = t.account_id "
//			+ "INNER JOIN (SELECT account_id, MAX(date) as max_date FROM Txn GROUP BY account_id) t1 "
//			+ "ON a.id = t1.account_id AND t.date = t1.max_date " + "WHERE a.user_id = :userId "
//			+ "GROUP BY a.id, a.name", nativeQuery = true)
//	List<Object[]> findAccountsAmountsByUserId(@Param("userId") Integer userId);

	@Query(value = "SELECT a.id, a.name, SUM(t.amount) as total_amount FROM asset a "
			+ "INNER JOIN Txn t ON a.id = t.asset_id "
			+ "INNER JOIN (SELECT asset_id, MAX(date) as max_date FROM Txn GROUP BY asset_id) t1 "
			+ "ON a.id = t1.asset_id AND t.date = t1.max_date " + "WHERE t.user_id = :userId "
			+ "GROUP BY a.id, a.name", nativeQuery = true)
	List<Object[]> findAssetsAmountsByUserId(@Param("userId") Integer userId);

}