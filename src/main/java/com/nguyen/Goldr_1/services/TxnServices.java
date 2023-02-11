package com.nguyen.Goldr_1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.repository.TxnRepo;

@Service
public class TxnServices {

	@Autowired
	private TxnRepo txnRepo;

	public List<Txn> getAllTxns() {
		List<Txn> txns = new ArrayList<Txn>();
		txnRepo.findAll().forEach(txns::add);
		return txns;
	}

	public Optional<Txn> getTxnById(Integer id) {
		return txnRepo.findById(id);
	}

	public void addTxn(Txn txn) {
		txnRepo.save(txn);
	}

	public void updateTxn(Integer id, Txn txn) {
		Optional<Txn> txnData = txnRepo.findById(id);

		if (txnData.isPresent()) {
			Txn _txn = txnData.get();
			_txn.setAmount(txn.getAmount());
			txnRepo.save(_txn);
		}
	}

	public void deleteTxn(Integer id) {
		txnRepo.deleteById(id);
	}

}
