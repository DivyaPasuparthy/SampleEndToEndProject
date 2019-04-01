package com.example.springboot.resource;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.entity.TerminatedUsersDTO;
import com.example.springboot.entity.Users;
import com.example.springboot.repository.UsersRepository;

@RestController
@RequestMapping(value = "/api/users")
public class UsersResource {

	private static final Logger log = LoggerFactory.getLogger(UsersResource.class);
	
	private static final String SUSPENDED = "suspended";
	@Autowired
	UsersRepository usersRepository;

	/*Get All Users*/
	@GetMapping(value = "/all")
	public ResponseEntity<List<Users>> getAllUsers() {

		List<Users> users = usersRepository.findAll();
		if(users.isEmpty()) {
			log.debug("No Users present in the database.");
			return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
		}
		log.debug("All Users are returned.");
		return new ResponseEntity<List<Users>>(users,HttpStatus.OK);		
		
	}
	
	
	/*Get User details By UserName*/
	@GetMapping(value = "/{username}")
	public ResponseEntity<?> getUserDetailsByUsername(@PathVariable("username") String userName) {

		Users user = usersRepository.findByUserName(userName);
		if( null == user) {
			log.debug("User is not present in the database with username - {}", userName);
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Users>(user,HttpStatus.OK);		
	}
	
	
	/*Create a New User*/
	@PutMapping
	public ResponseEntity<?> createUser(@RequestBody Users user){
		Users currentUser = usersRepository.findByUserName(user.getUserName());
		if( null == currentUser) {
			usersRepository.save(user);
			return new ResponseEntity<Users>(user,HttpStatus.OK);			
		}
		log.error("A user already exists with same username - {}", user.getUserName());
		return new ResponseEntity<Users>(HttpStatus.CONFLICT);
	}
	
	/*Update an Existing User*/
	@PostMapping
	public @ResponseBody ResponseEntity<?> updateUser(@RequestBody Users user){
		
		Users currentUser = usersRepository.findByUserName(user.getUserName());
		
		if( null != currentUser) {
			currentUser.setFirstName(user.getFirstName());
			currentUser.setLastName(user.getLastName());
			currentUser.setUserName(user.getUserName());
			currentUser.setStatus(user.getStatus());
			usersRepository.save(currentUser);
			return new ResponseEntity<Users>(currentUser,HttpStatus.OK);			
		}else {
			log.error("Requested user is not found in the database - {}", user.getUserName());
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);	
		}
		
	}
	
	/*Fetch a User by User name and Update their status to suspended*/
	@PostMapping(value = "/{username}")
	public ResponseEntity<?> updateUserStatus(@PathVariable("username") String userName, @RequestBody TerminatedUsersDTO terminatedUsersdto){
		
		Users currentUser = usersRepository.findByUserName(userName);
		
		if( null != currentUser) {
			currentUser.setStatus(SUSPENDED);
			usersRepository.save(currentUser);
			return new ResponseEntity<Users>(currentUser,HttpStatus.OK);
		}
		
		log.error("Requested User is not found - {}", userName);
		return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
	
	}
	
	/*Delete a User by UserName*/
	@DeleteMapping(value = "/{username}")
	public ResponseEntity<?> deleteUserByUsername(@PathVariable("username") String userName){
		
		Users user = usersRepository.findByUserName(userName);
		if( null == user) {
			log.error("User not found in the database with username -{}", userName);
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
		usersRepository.deleteById(user.getId());
		return new ResponseEntity<Users>(HttpStatus.NO_CONTENT); 
	}
	
	
	 

}
