package com.dating.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.dating.app.entity.User;
import com.dating.app.util.UserGender;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByGender(UserGender male);
	

}
