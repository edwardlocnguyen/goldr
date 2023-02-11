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
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountServices accountServices;

	@GetMapping(value = "/name/{name}", produces = "application/json")
	public List<Account> findByName(@PathVariable("name") String name) {
		return accountServices.findAccountsByName(name);
	}

//	@GetMapping
//	public List<Account> getAllAccounts() {
//		return accountServices.getAllAccounts();
//	}

//	@GetMapping("/{id}")
//	public Optional<Account> getAccountById(@PathVariable("id") Integer id) {
//		return accountServices.getAccountById(id);
//	}

	@PostMapping("/users/{id}/accounts")
	public void createAccount(@PathVariable("id") Integer userId, @RequestBody Account account) {
		accountServices.addAccount(userId, account);
	}

//	@PostMapping
//	public void createAccount(@RequestBody Account account, @RequestBody Integer userId) {
//		accountServices.addAccount(account, userId);
//	}
//
//	@PutMapping("/{id}")
//	public void updateAccount(@PathVariable("id") Integer id, @RequestBody Account account) {
//		accountServices.updateAccount(id, account);
//	}
//
//	@DeleteMapping("/{id}")
//	public void deleteAccount(@PathVariable("id") Integer id) {
//		accountServices.deleteAccount(id);
//	}

}
