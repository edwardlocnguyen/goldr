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
		try {
			return userRepo.findById(id);
		} catch (Exception e) {
			System.err.println("Error occurred while getting user by ID: " + e.getMessage());
			return Optional.empty();
		}
	}

	public void addUser(User user) {
		userRepo.save(user);
	}

	public void updateUser(Integer id, User user) {
		Optional<User> userData = userRepo.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			if (!user.getEmail().isEmpty()) {
				_user.setEmail(user.getEmail());
			}
			if (!user.getPassword().isEmpty()) {
				_user.setPassword(user.getPassword());
			}
			if (!user.getFirstName().isEmpty()) {
				_user.setFirstName(user.getFirstName());
			}
			if (!user.getLastName().isEmpty()) {
				_user.setLastName(user.getLastName());
			}
			if (!user.getOccupation().isEmpty()) {
				_user.setOccupation(user.getOccupation());
			}
			if (user.getDob() != null) {
				_user.setDob(user.getDob());
			}
			System.out.println("_user credit_score from services: " + user.getCreditScore());
			if (user.getCreditScore() != null) {
				_user.setCreditScore(user.getCreditScore());
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

	public List<Object[]> getAssetsAmountsByUserId(Integer userId) {
		return userRepo.findAssetsAmountsByUserId(userId);
	}

}
