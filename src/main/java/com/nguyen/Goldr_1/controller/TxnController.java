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

import com.nguyen.Goldr_1.model.Account;
import com.nguyen.Goldr_1.model.Asset;
import com.nguyen.Goldr_1.model.Txn;
import com.nguyen.Goldr_1.repository.AccountRepo;
import com.nguyen.Goldr_1.repository.AssetRepo;
import com.nguyen.Goldr_1.services.TxnServices;

//@RestController
@Controller
@RequestMapping("users/{userId}/txns") //	all ids are user ids for testing!!!
public class TxnController {

	@Autowired
	private TxnServices txnServices;
	@Autowired
	private AssetRepo assetRepo;
	@Autowired
	private AccountRepo accountRepo;

//	@GetMapping
//	public List<Txn> getAllTxns() {
//		return txnServices.getAllTxns();
//	}
//
//	@GetMapping("/{id}")
//	public Optional<Txn> getTxnById(@PathVariable("id") Integer id) {
//		return txnServices.getTxnById(id);
//	}

	@GetMapping("/form")
	public String getTxnForm(Model model, @PathVariable("userId") Integer userId) {
		List<Asset> userAssets = assetRepo.findByUserId(userId);
		List<Account> userAccounts = accountRepo.findByUserId(userId);
		
		model.addAttribute("txn", new Txn());
		model.addAttribute("userAssets", userAssets);
		model.addAttribute("userAccounts", userAccounts);
		
		return "txnForm";
	}

	@PostMapping("/post")
	public String createTxn(@RequestParam("userId") Integer userId, @ModelAttribute("txn") Txn txn) {
		txnServices.addTxn(userId, txn);
		
		return "redirect:/users/" + userId + "/accounts-amounts";
	}

//	@PostMapping
//	public void createTxn(@PathVariable("userId") Integer userId, @RequestBody Txn txn) {
//		txnServices.addTxn(userId, txn);
//	}

	@PutMapping("/{id}")
	public void updateTxn(@PathVariable("id") Integer id, @RequestBody Txn txn) {
		txnServices.updateTxn(id, txn);
	}

	@DeleteMapping("/{id}")
	public void deleteTxn(@PathVariable("id") Integer id) {
		txnServices.deleteTxn(id);
	}

}
