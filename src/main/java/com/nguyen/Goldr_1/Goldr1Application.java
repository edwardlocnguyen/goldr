package com.nguyen.Goldr_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.AssetRepo;
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

	}

}
