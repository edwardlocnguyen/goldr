package com.nguyen.Goldr_1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.UserRepo;

@Service
public class AccountServices {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private UserRepo userRepo;

	public List<Account> findAccountsByUserId(Integer userId) {
		return accountRepo.findByUserId(userId);
	}

	public Optional<Account> findAccountByUserId(Integer userId, Integer id) {
		return accountRepo.findByUserIdAndId(userId, id);
	}

	public void addAccount(Integer userId, Account account) {
		Optional<User> user = userRepo.findById(userId);
		if (user.isPresent()) {
			account.setUser(user.get());
			accountRepo.save(account);
		}
	}

	public void updateAccount(Integer userId, Integer id, Account account) {
		Optional<Account> accountData = accountRepo.findByUserIdAndId(userId, id);

		if (accountData.isPresent()) {
			Account _account = accountData.get();
			if (!account.getName().isEmpty()) {
				_account.setName(account.getName());
			}
			accountRepo.save(_account);
		}
	}

	public void deleteAccount(Integer id) {
		System.out.println("id: " + id);
		Optional<Account> account = accountRepo.findById(id);
		System.out.println("id: " + id);
		accountRepo.deleteById(id);
	}
}
