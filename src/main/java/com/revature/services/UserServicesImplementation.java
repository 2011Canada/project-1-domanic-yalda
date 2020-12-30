package com.revature.services;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Reimbursements;
import com.revature.models.User;
import com.revature.repositories.UserDao;

public class UserServicesImplementation implements UserServices{
	
	private UserDao ud;
	

	public UserServicesImplementation(UserDao ud) {
		this.ud = ud;
	}

	public User LoginCheck(String username, String password) throws UserNotFoundException, InternalErrorException {
		User  u = ud.findUserByUsernamePassword(username, password);

		return u;
	}

	public Reimbursements viewTicket() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean sendTicketStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<User> findAllEmployees() throws UserNotFoundException, InternalErrorException {
		return ud.findAllUsers();
	}
	

	public List<Reimbursements> findAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

}
