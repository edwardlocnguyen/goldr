package com.nguyen.Goldr_1.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.AssetRepo;
import com.nguyen.Goldr_1.services.UserServices;

import org.springframework.ui.Model;

//@RestControllers
@Controller
@RequestMapping("/users/{id}")
public class UserController {

	@Autowired
	private UserServices userServices;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private AccountRepo accountRepo;

//	CRUD methods
//	@GetMapping
//	public List<User> getAllUsers() {
//		return userServices.getAllUsers();
//	}

	@GetMapping("/home")
	public String userHome(@PathVariable("id") Integer id, Model model) {

		Optional<User> user = userServices.getUserById(id);
		User _user = user.get();
		LocalDate dob = _user.getDob();
		Integer age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());

		List<Asset> userAssets = assetRepo.findByUserId(id);
		List<Account> userAccounts = accountRepo.findByUserId(id);
		List<Object[]> userAccountsAmounts = userServices.getAccountsAmountsByUserId(id);

//		converting userAccountsAmounts from List of List to List of Obj
		List<Map<String, Object>> accountsList = new ArrayList<>();
		
		double totalAmount = 0;
		for (Object[] account : userAccountsAmounts) {
			Map<String, Object> accountMap = new HashMap<>();
			accountMap.put("id", account[0]);
			accountMap.put("name", account[1]);
			accountMap.put("asset", account[2]);
			accountMap.put("amount", account[3]);
			accountsList.add(accountMap);
			totalAmount += (double) account[3];
		}

		model.addAttribute("user", _user);
		model.addAttribute("id", id.toString());
		model.addAttribute("age", age);
		model.addAttribute("userAssets", userAssets);
		model.addAttribute("userAccounts", userAccounts);
		
		model.addAttribute("userAccountsAmounts", userAccountsAmounts);
		model.addAttribute("accountsList", accountsList);
		model.addAttribute("totalAmount", totalAmount);

		return "home";
	}

//	@PostMapping
//	public void createUser(@RequestBody User user) {
//		userServices.addUser(user);
//	}

	@PutMapping
	public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		userServices.updateUser(id, user);
	}

	@DeleteMapping
	public void deleteUser(@PathVariable("id") Integer id) {
		userServices.deleteUser(id);
	}

//	mapping to pages that use methods in UserControllerJSON
	@GetMapping("/accounts-amounts")
	public String userAccount(@PathVariable("id") Integer id, Model model) {
		List<Asset> userAssets = assetRepo.findByUserId(id);
		List<Account> userAccounts = accountRepo.findByUserId(id);

		model.addAttribute("id", id.toString());
		model.addAttribute("account", new Account());
		model.addAttribute("txn", new Txn());
		model.addAttribute("userAssets", userAssets);
		model.addAttribute("userAccounts", userAccounts);

		return "userAccount";
	}

	@GetMapping("/assets-amounts")
	public String userAsset(@PathVariable("id") Integer id, Model model) {
		List<Asset> userAssets = assetRepo.findByUserId(id);

		model.addAttribute("id", id.toString());
		model.addAttribute("asset", new Asset());
		model.addAttribute("userAssets", userAssets);

		return "userAsset";
	}

}
