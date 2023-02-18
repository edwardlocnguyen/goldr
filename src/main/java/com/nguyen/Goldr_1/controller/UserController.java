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
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.nguyen.Goldr_1.repository.UserRepo;
import com.nguyen.Goldr_1.services.TxnServices;
import com.nguyen.Goldr_1.services.UserServices;

import org.springframework.ui.Model;

//@RestControllers
@Controller
@RequestMapping("/users/{id}")
public class UserController {

	@Autowired
	private UserServices userServices;
	@Autowired
	private TxnServices txnServices;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private UserRepo userRepo;

//	CRUD methods

//	reusable fxn to get user's assets and their amounts
	public List<Map<String, Object>> calculateAssetAmountsAndTotal(List<Map<String, Object>> userLatestAccountTxns) {
		Map<Integer, Double> assetAmounts = new HashMap<>();
		for (Map<String, Object> txn : userLatestAccountTxns) {
			int assetId = (int) txn.get("assetId");
			double amount = (double) txn.get("amount");
			assetAmounts.put(assetId, assetAmounts.getOrDefault(assetId, 0.0) + amount);
		}

		List<Map<String, Object>> assetAmountsList = new ArrayList<>();
		for (Map.Entry<Integer, Double> entry : assetAmounts.entrySet()) {
			int assetId = entry.getKey();
			double totalAmount = entry.getValue();
			Asset asset = assetRepo.findById(assetId).get();
			Map<String, Object> assetAmountMap = new HashMap<>();
			assetAmountMap.put("assetName", asset.getName());
			assetAmountMap.put("totalAmount", String.format("%.2f", totalAmount));
			assetAmountsList.add(assetAmountMap);
		}

		return assetAmountsList;
	}

//	fxn to get the Total amount via the user's latest txns
	public double calculateTotalAmount(List<Map<String, Object>> userLatestAccountTxns) {
		double totalAmount = 0.0;
		for (Map<String, Object> txn : userLatestAccountTxns) {
			totalAmount += (double) txn.get("amount");
		}
		return totalAmount;
	}

//	fxn to get the user's age
	public int calculateUserAge(User _user) {
		LocalDate dob = _user.getDob();
		int age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
		return age;
	}

	@GetMapping("/home")
	public String userHome(@PathVariable("id") Integer id, Model model) {

//		get user data
		Optional<User> user = userServices.getUserById(id);
		User _user = user.get();
		int age = calculateUserAge(_user);

		// get user's latest txns
		List<Map<String, Object>> userLatestAccountTxns = txnServices.getAccountTxnsByUserId(id);

//		call the fxn above to filter user's assets and their amounts
		List<Map<String, Object>> assetAmountsAndTotal = calculateAssetAmountsAndTotal(userLatestAccountTxns);

//		get the total amount for the table
		double totalAmount = calculateTotalAmount(userLatestAccountTxns);

//		calculate the percentage of each asset amount over the total amount for the table
		for (Map<String, Object> assetAmountMap : assetAmountsAndTotal) {
			double assetAmount = Double.parseDouble((String) assetAmountMap.get("totalAmount"));
			double assetPercentage = assetAmount / totalAmount * 100.0;
			assetAmountMap.put("assetPercentage", String.format("%.2f", assetPercentage));
		}

		model.addAttribute("user", _user);
		model.addAttribute("id", id.toString());
		model.addAttribute("age", age);
		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("userLatestAccountTxns", userLatestAccountTxns);
		model.addAttribute("assetAmountsAndTotal", assetAmountsAndTotal);

		return "home";
	}

	@GetMapping("/profile")
	public String userProfile(@PathVariable("id") Integer id, Model model) {

//		get user data
		Optional<User> user = userServices.getUserById(id);
		User _user = user.get();
		int age = calculateUserAge(_user);

		model.addAttribute("user", _user);
		model.addAttribute("id", id.toString());
		model.addAttribute("age", age);

		return "profile";
	}

	@GetMapping("/accounts-amounts")
	public String userAccount(@PathVariable("id") Integer id, Model model) {

//		for the create new txn form, but might not need once i change the model relationships
//			changing model relationships so that one account can only have one asset (category)
		List<Asset> userAssets = assetRepo.findByUserId(id);
//		get the user's accounts for the create new txn form, and delete buttons
		List<Account> userAccounts = accountRepo.findByUserId(id);

//		get the user's latest txns for the table
		List<Map<String, Object>> userLatestAccountTxns = txnServices.getAccountTxnsByUserId(id);

//		get the total amount for the table
		double totalAmount = calculateTotalAmount(userLatestAccountTxns);

//		id is used for all forms, account/txn used for respective create forms
		model.addAttribute("id", id.toString());
		model.addAttribute("account", new Account());
		model.addAttribute("txn", new Txn());

		model.addAttribute("userAssets", userAssets);
		model.addAttribute("userAccounts", userAccounts);

		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("userLatestAccountTxns", userLatestAccountTxns);

		return "userAccount";
	}

	@GetMapping("/assets-amounts")
	public String userAsset(@PathVariable("id") Integer id, Model model) {

//		get the user's assets for the delete buttons
		List<Asset> userAssets = assetRepo.findByUserId(id);

//		get the user's latest txns for the table
		List<Map<String, Object>> userLatestAccountTxns = txnServices.getAccountTxnsByUserId(id);
		List<Map<String, Object>> assetAmountsAndTotal = calculateAssetAmountsAndTotal(userLatestAccountTxns);

//		get the total amount for the table
		double totalAmount = calculateTotalAmount(userLatestAccountTxns);

//		calculate the percentage of each asset amount over the total amount for the table
		for (Map<String, Object> assetAmountMap : assetAmountsAndTotal) {
			double assetAmount = Double.parseDouble((String) assetAmountMap.get("totalAmount"));
			double assetPercentage = assetAmount / totalAmount * 100.0;
			assetAmountMap.put("assetPercentage", String.format("%.2f", assetPercentage));
		}

//		id is used for all forms, asset is used for create form
		model.addAttribute("id", id.toString());
		model.addAttribute("asset", new Asset());

		model.addAttribute("totalAmount", totalAmount);
		model.addAttribute("userAssets", userAssets);
		model.addAttribute("assetAmountsAndTotal", assetAmountsAndTotal);

		return "userAsset";
	}

//	@PostMapping
//	public void createUser(@RequestBody User user) {
//		userServices.addUser(user);
//	}

	@PutMapping("/update")
	public String updateUser(@PathVariable("id") Integer id, @ModelAttribute User user) {

		Optional<User> userData = userRepo.findById(id);
		System.out.println("user first_name from controller: " + user.getFirstName());
		System.out.println("user last_name from controller: " + user.getLastName());
		System.out.println("user credit_score from controller: " + user.getCreditScore());

		userServices.updateUser(id, user);
		return "redirect:/users/" + id + "/profile";
	}

	@DeleteMapping
	public void deleteUser(@PathVariable("id") Integer id) {
		userServices.deleteUser(id);
	}

}
