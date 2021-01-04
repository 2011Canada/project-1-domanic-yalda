package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Reimbursements;
import com.revature.models.User;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.UserDao;

public class UserServicesImplementation implements UserServices{
	
	private UserDao ud;
	private ReimbursementDao rd;

	public UserServicesImplementation(UserDao ud) {
		this.ud = ud;
	}
	public UserServicesImplementation(UserDao ud, ReimbursementDao rd) {
		this.ud = ud;
		this.rd = rd;
		
	}
	public UserServicesImplementation(ReimbursementDao rd) {
		this.rd = rd;
	}
	

	public User loginCheck(String username, String password) throws UserNotFoundException, InternalErrorException {
		User  u = ud.findUserByUsernamePassword(username, password);
		return u;
	}

	//employee view tickets
	public List<Reimbursements> viewTicket(User u) {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		//should have user data at this point anyways
		r = rd.findAllReimbursementsByUserId(u);
		return r;
	}

	//manager approving or denying to update a status 
	public Reimbursements sendTicketStatusApproved(Reimbursements r ) {
		rd.UpdateStatusApproved(r);
		return r;
	}
	public Reimbursements sendTicketStatusDenied(Reimbursements r) {
		rd.UpdateStatusDenied(r);
		return r;
	}

	public List<User> findAllEmployees() throws UserNotFoundException, InternalErrorException {
		return ud.findAllEmployees();
	}
	

	//manager viewing all the reimbursements
	public List<Reimbursements> findAllReimbursements() {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		r = rd.findAllReimbursements();
		return r;
	}
	
	public void makeReimbursementCliam(double amount, String type, String description, User u) {
		rd.addReimbursement(amount, type, description, u);
	}

}
