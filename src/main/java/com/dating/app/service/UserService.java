package com.dating.app.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dating.app.dao.UserDao;
import com.dating.app.dto.MatchingUser;
import com.dating.app.entity.User;
import com.dating.app.sort.UserSorting;
import com.dating.app.util.UserGender;
@Service
public class UserService {
 @Autowired
 UserDao userdao;

 public ResponseEntity<?> saveUser(User user) {
	
	User savedUser=userdao.saveUser(user);
	return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
 }

 
 public ResponseEntity<?> findAllMaleUsers() {
	 
	 List<User> maleusers=userdao.findAllMaleUsers();
	 if(maleusers.isEmpty()) {
		 
		 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no male user presnt inthe base table");
	 }
	 else {
		 return ResponseEntity.status(HttpStatus.OK).body(maleusers);
	 }
 }


 public ResponseEntity<?> findAllFemaleUsers() {
List<User> femaleusers=userdao.findAllFemaleUsers();
if(femaleusers.isEmpty()) {
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no female user presnt inthe base table");

}
	return ResponseEntity.status(HttpStatus.OK).body(femaleusers);
 }


 public ResponseEntity<?> findBestMatch(int id, int top) {
	Optional<User> optional=userdao.findUserById(id);
	if(optional.isEmpty()) 
	{
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid id");
	}
	User user=optional.get();
	List<User> users=null;
	if(user.getGender().equals(UserGender.MALE))
	{
		users=userdao.findAllFemaleUsers();
	}
	else {
		users=userdao.findAllMaleUsers();
	}
	List<MatchingUser> matchingUser=new ArrayList<>();
	for(User u:users) {
		MatchingUser mu=new MatchingUser();
		mu.setId(u.getId());
		mu.setName(u.getName());
		mu.setEmail(u.getEmail());
		mu.setPhone(u.getPhone());
		mu.setGender(u.getGender());
		mu.setInterests(u.getInterests());
		mu.setGender(u.getGender());
		
		mu.setAgediff(Math.abs(user.getAge()-u.getAge()));
		
		List<String> interest1=user.getInterests();
		List<String> interest2=user.getInterests();
		
		int mic=0;
		for(String s:interest1) {
			if(interest2.contains(s)) {
				mic++;
			}
		}
		mu.setMic(mic);
		matchingUser.add(mu);
		
	}
	Comparator<MatchingUser> c=new UserSorting();
	Collections.sort(matchingUser, c);
	List<MatchingUser> result=new ArrayList<>();
	for(MatchingUser mu:matchingUser)
	{
	if(top==0) {
		break;
	}
	else {
		result.add(mu);
		top--;
	}
	}
	return ResponseEntity.status(HttpStatus.OK).body(result);
 }
}
