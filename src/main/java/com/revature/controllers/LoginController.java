package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserPostgresDao;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImplementation;

public class LoginController {

	
	private UserServices us = new UserServicesImplementation(new UserPostgresDao());
	
	private ObjectMapper om = new ObjectMapper();
	
	//actually do the request
	public void userLogin(HttpServletRequest req, HttpServletResponse res) throws IOException, UserNotFoundException, InternalErrorException {
		User u = om.readValue(req.getInputStream(), User.class);
		u = us.loginCheck(u.getUsername(), u.getPassword());
		//use your session to keep track of your user permission level
		HttpSession sess = req.getSession();
		//user.getRole
		sess.setAttribute("User", u);
		
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(u));
	}
}
