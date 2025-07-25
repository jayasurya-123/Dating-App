package com.dating.app.controllerr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dating.app.entity.User;
import com.dating.app.repository.UserRepository;
import com.dating.app.service.UserService;

@RestController
public class UserController {

    private final UserRepository userRepository;
@Autowired
UserService userservice;


    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


@PostMapping("/users")
public ResponseEntity<?> saveUser(@RequestBody User user) {
	return userservice.saveUser(user);
	
}
@GetMapping("/users/gender/male")
public ResponseEntity<?> findAllMaleUsers(){
	return userservice.findAllMaleUsers();
}
@GetMapping("/users/gender/female")
	public ResponseEntity<?> findAllFemaleUsers(){
	return userservice.findAllFemaleUsers();
	}

@GetMapping("/users/best-match/{id}/{top}")
public ResponseEntity<?> findBestMatch(@PathVariable int id,@PathVariable int top){
	return userservice.findBestMatch(id,top);
}
@GetMapping("/users/search/name/{letters}")
public ResponseEntity<?> searchByName(@PathVariable String letters)
{

	return userservice.searchByName(letters);
}
@GetMapping("/users/search/email/{letters}")
public ResponseEntity<?> searchByEmail(@PathVariable String letters){
	return userservice.searchByEmail(letters);
}

}
