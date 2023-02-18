//package com.nguyen.Goldr_1.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nguyen.Goldr_1.model.Account;
//import com.nguyen.Goldr_1.services.AccountServices;
//
//@RestController
//@RequestMapping("/users/{id}/api")
//public class AccountControllerJSON {
//
//	@Autowired
//	private AccountServices accountServices;
//
//	@GetMapping("/accounts")
//	public List<Account> getAccountsByUserId(@PathVariable("id") Integer userId) {
//		return accountServices.getAccountsByUserId(userId);
//	}
//
//}
