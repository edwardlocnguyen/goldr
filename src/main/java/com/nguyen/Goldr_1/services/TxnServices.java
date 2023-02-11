package com.nguyen.Goldr_1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.AssetRepo;
import com.nguyen.Goldr_1.repository.TxnRepo;
import com.nguyen.Goldr_1.repository.UserRepo;

@Service
public class TxnServices {

	@Autowired
	private TxnRepo txnRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private AccountRepo accountRepo;

	public List<Txn> getAllTxns() {
		List<Txn> txns = new ArrayList<Txn>();
		txnRepo.findAll().forEach(txns::add);
		return txns;
	}

	public Optional<Txn> getTxnById(Integer id) {
		return txnRepo.findById(id);
	}

	public void addTxn(Integer userId, Txn txn) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			Optional<Asset> asset = assetRepo.findById(txn.getAsset().getId());
			Optional<Account> account = accountRepo.findById(txn.getAccount().getId());
			if (asset.isPresent() && account.isPresent()) {
				txn.setUser(user.get());
				txn.setAsset(asset.get());
				txn.setAccount(account.get());
				txnRepo.save(txn);
			}
		}
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
