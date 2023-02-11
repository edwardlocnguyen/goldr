package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nguyen.Goldr_1.model.Txn;

@Repository
public interface TxnRepo extends JpaRepository<Txn, Integer> {

}
