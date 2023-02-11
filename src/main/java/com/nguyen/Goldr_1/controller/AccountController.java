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

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.services.AccountServices;

@RestController
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

	@Autowired
	private AccountServices accountServices;

	@GetMapping()
	public List<Account> getAccountsByUserId(@PathVariable("userId") Integer userId) {
		return accountServices.findAccountsByUserId(userId);
	}

	@GetMapping("/{id}")
	public Optional<Account> getAccountById(@PathVariable("userId") Integer userId, @PathVariable("id") Integer id) {
		return accountServices.findAccountByUserId(userId, id);
	}

	@PostMapping
	public void createAccount(@PathVariable("userId") Integer userId, @RequestBody Account account) {
		accountServices.addAccount(userId, account);
	}

	@PutMapping("/{id}")
	public void updateAccount(@PathVariable("userId") Integer userId, @PathVariable("id") Integer id,
			@RequestBody Account account) {
		accountServices.updateAccount(userId, id, account);
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable("id") Integer id) {
		accountServices.deleteAccount(id);
	}

}
