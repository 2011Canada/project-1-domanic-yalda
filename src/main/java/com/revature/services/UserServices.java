package com.revature.services;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Reimbursements;
import com.revature.models.User;

public interface UserServices {

	public User loginCheck(String username, String password) throws UserNotFoundException, InternalErrorException; // any user login credentials
	
	public List<User> findAllEmployees() throws UserNotFoundException, InternalErrorException;
	
	public List<Reimbursements> findAllReimbursements();
	
	public Reimbursements viewTicket(); // employee viewing past request
	
	public Reimbursements sendTicketStatusApproved(Reimbursements r); // manager approving
	public Reimbursements sendTicketStatusDenied(Reimbursements r); // manager denying 
}
