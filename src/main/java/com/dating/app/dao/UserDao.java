package com.dating.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dating.app.entity.User;
import com.dating.app.repository.UserRepository;

import com.dating.app.util.UserGender;

@Repository
public class UserDao {

	@Autowired
	UserRepository 	userrepository;

	public User saveUser(User user) {
		
		return userrepository.save(user);
	}

	public List<User> findAllMaleUsers(){
		
		return userrepository.findByGender(UserGender.MALE);
	}

	public List<User> findAllFemaleUsers() {
		
		return userrepository.findByGender(UserGender.FEMALE);
	}

	public Optional<User> findUserById(int id) {
		
		return userrepository.findById(id);
	}

	
	
}
