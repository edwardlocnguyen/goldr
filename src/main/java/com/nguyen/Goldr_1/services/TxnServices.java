package com.nguyen.Goldr_1.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	public List<Map<String, Object>> getTxnsByUserId(Integer userId) {
		Optional<User> user = userRepo.findById(userId);
		User _user = user.get();

		List<Map<String, Object>> latestTxns = new ArrayList<>();

		// Loop through each account
		for (Account account : _user.getAccounts()) {
			LocalDate latestDate = LocalDate.MIN;
			Txn latestTxn = null;

			// Loop through each transaction for this account
			for (Txn txn : account.getTxns()) {
				if (txn.getDate().isAfter(latestDate)) {
					latestDate = txn.getDate();
					latestTxn = txn;
				}
			}

			// Add the latest transaction for this account to the final list
			if (latestTxn != null) {
				Map<String, Object> txnData = new HashMap<>();
				txnData.put("id", latestTxn.getId());
				txnData.put("amount", latestTxn.getAmount());
				txnData.put("date", latestTxn.getDate());
				txnData.put("accountId", latestTxn.getAccount().getId());
				txnData.put("accountName", latestTxn.getAccount().getName());
				txnData.put("assetId", latestTxn.getAsset().getId());
				txnData.put("assetName", latestTxn.getAsset().getName());
				latestTxns.add(txnData);
			}
		}

		return latestTxns;
	}

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
			try {
				Optional<Asset> asset = assetRepo.findById(txn.getAsset().getId());
				Optional<Account> account = accountRepo.findById(txn.getAccount().getId());
				if (asset.isPresent() && account.isPresent()) {
					txn.setUser(user.get());
					txn.setAsset(asset.get());
					txn.setAccount(account.get());
					txnRepo.save(txn);
				}
			} catch (NullPointerException e) {
				System.out.println("Asset or account is null");
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
