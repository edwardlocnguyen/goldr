package com.nguyen.Goldr_1.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyen.Goldr_1.model.User;
import com.nguyen.Goldr_1.repository.UserRepo;

@Service
public class UserServices {

	@Autowired
	private UserRepo userRepo;

//	CRUD methods
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		userRepo.findAll().forEach(users::add);
		return users;
	}

	public Optional<User> getUserById(Integer id) {
		return userRepo.findById(id);
	}

	public void addUser(User user) {
		userRepo.save(user);
	}

	public void updateUser(Integer id, User user) {
		Optional<User> userData = userRepo.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			if (!user.getUsername().isEmpty()) {
				_user.setUsername(user.getUsername());
			}
			if (!user.getPassword().isEmpty()) {
				_user.setPassword(user.getPassword());
			}
			if (!user.getEmail().isEmpty()) {
				_user.setEmail(user.getEmail());
			}
			if (user.getAge() != null) {
				_user.setAge(user.getAge());
			}
			userRepo.save(_user);
		}
	}

	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}

//	more complex methods
	public List<Object[]> getAccountsAmountsByUserId(Integer userId) {
		return userRepo.findAccountsAmountsByUserId(userId);
	}

}
