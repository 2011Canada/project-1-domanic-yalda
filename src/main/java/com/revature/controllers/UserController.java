package com.revature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UnauthorizedException;
import com.revature.models.Reimbursements;
import com.revature.models.User;
import com.revature.repositories.ReimbursementPostgresDao;
import com.revature.repositories.UserPostgresDao;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImplementation;

public class UserController {
	
	private UserServices us = new UserServicesImplementation(new UserPostgresDao());
	
	private ObjectMapper om = new ObjectMapper();
	

	//manager getting the list of employees
	public void findAllUsers(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException {
		List<User> employees = new ArrayList<User>(); 
		HttpSession sess = req.getSession();
		
		User u = om.readValue(req.getInputStream(), User.class);
		if (u.getUserRole().equals("employee")) {
			employees = us.findAllEmployees();
		}else {
			throw new UnauthorizedException();
		}
		sess.setAttribute("Employes", employees);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(employees));
		
	}
	
	//manager viewing all reimbursement claims
	public void viewAllReimbursementsClaims(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException {
		List<Reimbursements> r = new ArrayList<Reimbursements>();
		HttpSession sess = req.getSession();
		User u = om.readValue(req.getInputStream(), User.class);
		if (u.getUserRole().equals("employee")) {
			r = us.findAllReimbursements();
		}
		//use your session to keep track of your session
		sess.setAttribute("Reimbursements", r);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(u));
		
	}
	
	//user checking the claims they made
	public void viewUserReimbursements(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException {
		List<Reimbursements> tickets = new ArrayList<Reimbursements>();
		HttpSession sess = req.getSession();
		User u = om.readValue(req.getInputStream(), User.class);
		if (u.getUserRole().equals("employee")) {
			tickets = us.viewTicket(u);
		}
		//use your session to keep track session
		sess.setAttribute("Claims", tickets);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(u));
		
	}
	
	//manager approving a claim
	public void aprroveReimbursement(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException {
		//Reimbursements r = new Reimbursements();
		HttpSession sess = req.getSession();
		User u = om.readValue(req.getInputStream(), User.class);
		if (u.getUserRole().equals("manager")) {
			us.sendTicketStatusApproved((Reimbursements) req); // request to approve has selected reimbursement to approve
		}
		//use your session to keep track of your user permission level
		//sess.setAttribute("Approved", ticket);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(u));
		
	}
	//manager denying a claim
	public void denyReimbursement(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException {
		//Reimbursements r = new Reimbursements();
		HttpSession sess = req.getSession();
		Reimbursements r = om.readValue(req.getInputStream(), Reimbursements.class);
		User u = om.readValue(req.getInputStream(), User.class);
		if (u.getUserRole().equals("manager")) {
			us.sendTicketStatusDenied(r); // request to approve has selected reimbursement to approve
		}
		//use your session to keep track of your user permission level
		//sess.setAttribute("Denied", ticket);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(r));
		
	}
	
	public void addReimbursement(HttpServletRequest req, HttpServletResponse res)throws IOException, UnauthorizedException, UnauthenticatedException{
		HttpSession sess = req.getSession();
		User u = om.readValue(req.getInputStream(), User.class);
		Reimbursements r = om.readValue(req.getInputStream(), Reimbursements.class);
		if (u.getUserRole().equals("employee")) {
			us.makeReimbursementCliam(r.getReimbursementAmount(), r.getReimbursementType(), r.getReimbursementDescription(), u); // request to approve has selected reimbursement to approve
		}
		sess.setAttribute("new reimbursement", r);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(r));
	}

}
