package com.nguyen.Goldr_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nguyen.Goldr_1.model.Txn;

public interface TxnRepo extends JpaRepository<Txn, Integer> {

}
