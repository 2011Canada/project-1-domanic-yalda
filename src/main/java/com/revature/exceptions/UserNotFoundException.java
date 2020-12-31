package com.revature.exceptions;

public class UserNotFoundException extends AbstractHttpException {

	public UserNotFoundException() {
		super("user not found", 404);
		// TODO Auto-generated constructor stub
	}

}
