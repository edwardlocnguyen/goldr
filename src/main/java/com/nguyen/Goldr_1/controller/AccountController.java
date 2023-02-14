package com.nguyen.Goldr_1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.services.AccountServices;

//@RestController
@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

	@Autowired
	private AccountServices accountServices;

//	@GetMapping()
//	public List<Account> getAllAccounts() {
//		return accountServices.getAllAccounts();
//	}
//
//	@GetMapping("/{id}")
//	public Optional<Account> getAccountById(@PathVariable("id") Integer id) {
//		return accountServices.getAccountById(id);
//	}

	@GetMapping("/form")
	public String getAccountForm(Model model) {
		model.addAttribute("account", new Account());
		return "accountForm";
	}

	@PostMapping("/post")
	public String createAccount(@RequestParam("userId") Integer userId, @ModelAttribute("account") Account account) {
		accountServices.addAccount(userId, account);
		return "redirect:/users/" + userId + "/accounts-amounts";
	}

//	@PostMapping
//	public void createAccount(@PathVariable("userId") Integer userId, @RequestBody Account account) {
//		accountServices.addAccount(userId, account);
//	}

	@PutMapping("/{id}")
	public void updateAccount(@PathVariable("id") Integer id, @RequestBody Account account) {
		accountServices.updateAccount(id, account);
	}

	@DeleteMapping("/{id}")
	public void deleteAccount(@PathVariable("id") Integer id) {
		accountServices.deleteAccount(id);
	}

}
