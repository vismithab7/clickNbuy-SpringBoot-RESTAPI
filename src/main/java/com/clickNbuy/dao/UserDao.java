package com.clickNbuy.dao;

import org.springframework.stereotype.Repository;

import com.clickNbuy.entity.User;
import com.clickNbuy.exception.DataNotFoundException;
import com.clickNbuy.repository.UserRepository;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class UserDao {
	
	UserRepository userRepository;

	public boolean isEmailUnique(String email) {
		
		return !userRepository.existsByEmail(email);
	}

	public boolean isMobileUnique(Long mobile) {
		
		return !userRepository.existsByMobile(mobile);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
		
	}

	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(()->new DataNotFoundException("Email Does'not found"));
		
	}
	
	public boolean isEmailAndMobileUnique(String email,Long mobile) {
		return !userRepository.existsByEmailOrMobile(email,mobile);
	}

	
}
