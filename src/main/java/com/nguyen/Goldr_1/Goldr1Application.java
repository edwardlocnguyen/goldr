package com.nguyen.Goldr_1;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.AssetRepo;
import com.nguyen.Goldr_1.repository.TxnRepo;
import com.nguyen.Goldr_1.repository.UserRepo;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class Goldr1Application {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private TxnRepo txnRepo;

	public static void main(String[] args) {
		SpringApplication.run(Goldr1Application.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void insertData() {

//		create user
		User user = new User();
		user.setUsername("eln94");
		user.setPassword("password1");
		user.setEmail("eln94@gmail.com");
		user.setAge(28);
		userRepo.save(user);

//		create assets
		Asset asset1 = new Asset();
		asset1.setName("cash");
		assetRepo.save(asset1);

		Asset asset2 = new Asset();
		asset2.setName("stocks");
		assetRepo.save(asset2);

//		create accounts
		Account account1 = new Account();
		account1.setName("PNC Checking");
//		frontend will set user either via URL params or in JSON
		account1.setUser(user);
		accountRepo.save(account1);

		Account account2 = new Account();
		account2.setName("Schwab Brokerage");
		account2.setUser(user);
		accountRepo.save(account2);

//		create transactions
		Txn txn1 = new Txn();
		txn1.setAmount(100.01);
		txn1.setDate(LocalDate.of(2023,1,1));
		txn1.setAsset(asset1);
		txn1.setAccount(account1);
//		frontend will set user either via URL params or in JSON
		txn1.setUser(user);
		txnRepo.save(txn1);
		
		Txn txn2 = new Txn();
		txn2.setAmount(5000.01);
		txn2.setDate(LocalDate.of(2023,1,1));
		txn2.setAsset(asset2);
		txn2.setAccount(account2);
		txn2.setUser(user);
		txnRepo.save(txn2);
		
		Txn txn3 = new Txn();
		txn3.setAmount(200.01);
		txn3.setDate(LocalDate.of(2023,2,1));
		txn3.setAsset(asset1);
		txn3.setAccount(account1);
		txn3.setUser(user);
		txnRepo.save(txn3);
		
		Txn txn4 = new Txn();
		txn4.setAmount(6000.01);
		txn4.setDate(LocalDate.of(2023,2,1));
		txn4.setAsset(asset2);
		txn4.setAccount(account2);
		txn4.setUser(user);
		txnRepo.save(txn4);

	}

}
