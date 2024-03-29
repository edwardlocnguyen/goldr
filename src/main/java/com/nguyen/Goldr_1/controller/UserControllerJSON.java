package com.nguyen.Goldr_1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyen.Goldr_1.services.UserServices;

@RestController
@RequestMapping("/users/{id}/api")
public class UserControllerJSON {

	@Autowired
	private UserServices userServices;

	@GetMapping("/accounts-amounts")
	public List<Object[]> getAccountsAmountsByUserId(@PathVariable("id") Integer userId) {
		return userServices.getAccountsAmountsByUserId(userId);
	}

	@GetMapping("/assets-amounts")
	public List<Object[]> getAssetsAmountsByUserId(@PathVariable("id") Integer userId) {
		return userServices.getAssetsAmountsByUserId(userId);
	}

}
