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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.UserRepo;
import com.nguyen.Goldr_1.services.AccountServices;

//@RestController
@Controller
@RequestMapping("/users/{userId}/accounts")
public class AccountController {

	@Autowired
	private AccountServices accountServices;
	@Autowired
	private UserRepo userRepo;

	@GetMapping()
	public List<Account> getAccountsByUserId(@PathVariable("userId") Integer userId) {
		return accountServices.findAccountsByUserId(userId);
	}

	@GetMapping("/{id}")
	public Optional<Account> getAccountById(@PathVariable("userId") Integer userId, @PathVariable("id") Integer id) {
		return accountServices.findAccountByUserId(userId, id);
	}
	
	@GetMapping("/form")
	public String getAccountForm(Model model) {
		model.addAttribute("account", new Account());
		return "accountForm";
	}

	@PostMapping("/post")
	public String createAccount(@RequestParam("userId") Integer userId, @ModelAttribute("account") Account account) {
		accountServices.addAccount(userId, account);
		return "redirect:/users/user-account/" + userId;
	}

//	@PostMapping
//	public void createAccount(@PathVariable("userId") Integer userId, @RequestBody Account account) {
//		accountServices.addAccount(userId, account);
//	}

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
