package com.nguyen.Goldr_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.services.TxnServices;

@RestController
@RequestMapping("/txns")
public class TxnController {

	@Autowired
	private TxnServices txnServices;

	@GetMapping
	public List<Txn> getAllTxns() {
		return txnServices.getAllTxns();
	}

	@GetMapping("/{id}")
	public Optional<Txn> getTxnById(@PathVariable("id") Integer id) {
		return txnServices.getTxnById(id);
	}

	@PostMapping
	public void createTxn(@RequestBody Txn txn) {
		txnServices.addTxn(txn);
	}

	@PutMapping("/{id}")
	public void updateTxn(@PathVariable("id") Integer id, @RequestBody Txn txn) {
		txnServices.updateTxn(id, txn);
	}

	@DeleteMapping("/{id}")
	public void deleteTxn(@PathVariable("id") Integer id) {
		txnServices.deleteTxn(id);
	}

}
