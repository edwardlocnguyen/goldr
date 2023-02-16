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
		User user1 = new User();
		user1.setUsername("eln94");
		user1.setPassword("password1");
		user1.setFirstName("Edward");
		user1.setLastName("Nguyen");
		user1.setEmail("eln94@gmail.com");
		user1.setAge(28);
		userRepo.save(user1);

		User user2 = new User();
		user2.setUsername("kkn89");
		user2.setPassword("password2");
		user2.setFirstName("Kevin");
		user2.setLastName("Nguyen");
		user2.setEmail("kkn89@gmail.com");
		user2.setAge(33);
		userRepo.save(user2);

//		create assets
		Asset asset1 = new Asset();
		asset1.setName("cash");
		asset1.setUser(user1);
		assetRepo.save(asset1);

		Asset asset2 = new Asset();
		asset2.setName("investments");
		asset2.setUser(user1);
		assetRepo.save(asset2);

		Asset asset3 = new Asset();
		asset3.setName("cash");
		asset3.setUser(user2);
		assetRepo.save(asset3);

//		create accounts
		Account account1 = new Account();
		account1.setName("PNC Checking");
//		frontend will set user either via URL params or in JSON
		account1.setUser(user1);
		accountRepo.save(account1);

		Account account2 = new Account();
		account2.setName("Schwab Brokerage");
		account2.setUser(user1);
		accountRepo.save(account2);

		Account account3 = new Account();
		account3.setName("Chase Savings");
		account3.setUser(user2);
		accountRepo.save(account3);

//		create transactions
		Txn txn1 = new Txn();
		txn1.setAmount(100.01);
		txn1.setDate(LocalDate.of(2023, 1, 1));
		txn1.setAsset(asset1);
		txn1.setAccount(account1);
//		frontend will set user either via URL params or in JSON
		txn1.setUser(user1);
		txnRepo.save(txn1);

		Txn txn2 = new Txn();
		txn2.setAmount(5000.01);
		txn2.setDate(LocalDate.of(2023, 1, 1));
		txn2.setAsset(asset2);
		txn2.setAccount(account2);
		txn2.setUser(user1);
		txnRepo.save(txn2);

		Txn txn3 = new Txn();
		txn3.setAmount(200.01);
		txn3.setDate(LocalDate.of(2023, 2, 1));
		txn3.setAsset(asset1);
		txn3.setAccount(account1);
		txn3.setUser(user1);
		txnRepo.save(txn3);

		Txn txn4 = new Txn();
		txn4.setAmount(6000.01);
		txn4.setDate(LocalDate.of(2023, 2, 1));
		txn4.setAsset(asset2);
		txn4.setAccount(account2);
		txn4.setUser(user1);
		txnRepo.save(txn4);

		Txn txn5 = new Txn();
		txn5.setAmount(100000.01);
		txn5.setDate(LocalDate.of(2023, 1, 15));
		txn5.setAsset(asset3);
		txn5.setAccount(account3);
		txn5.setUser(user2);
		txnRepo.save(txn5);
		
		Txn txn6 = new Txn();
		txn6.setAmount(10000.02);
		txn6.setDate(LocalDate.of(2023, 1, 15));
		txn6.setAsset(asset3);
		txn6.setAccount(account3);
		txn6.setUser(user2);
		txnRepo.save(txn6);

	}

}
