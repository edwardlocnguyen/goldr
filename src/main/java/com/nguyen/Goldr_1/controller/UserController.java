package com.nguyen.Goldr_1.controller;

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

import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.services.UserServices;

import org.springframework.ui.Model;

//@RestControllers
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServices userServices;

//	CRUD methods
	@GetMapping
	public List<User> getAllUsers() {
		return userServices.getAllUsers();
	}

	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") Integer id) {
		return userServices.getUserById(id);
	}

	@PostMapping
	public void createUser(@RequestBody User user) {
		userServices.addUser(user);
	}

	@PutMapping("/{id}")
	public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
		userServices.updateUser(id, user);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id") Integer id) {
		userServices.deleteUser(id);
	}

//	mapping to pages that use methods in UserControllerJSON
	@GetMapping("/user-account/{userId}")
	public String userAccount(@PathVariable("userId") Integer userId, Model model) {
		model.addAttribute("userId", userId.toString());
		return "userAccount";
	}

	@GetMapping("/user-asset/{userId}")
	public String userAsset(@PathVariable("userId") Integer userId, Model model) {
		model.addAttribute("userId", userId.toString());
		return "userAsset";
	}

}
