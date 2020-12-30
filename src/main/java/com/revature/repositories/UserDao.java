package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

public interface UserDao {
	
	//public User addUser(User u); 
	//no need to add a user from the backend, user (employees) added to db at time of hire, no registration option
	
	public List<User> findAllUsers() throws UserNotFoundException, InternalErrorException;	
	
	public User findUserByUsernamePassword(String username, String password) throws UserNotFoundException, InternalErrorException;
	
	public List<User> findByTypeOfUser(String type) throws UserNotFoundException, InternalErrorException;
	
	
}
