package com.revature.exceptions;

public class ReimbursementNotFoundException extends AbstractHttpException {

	protected ReimbursementNotFoundException() {
		super("Ticket claim not found", 404);
		// TODO Auto-generated constructor stub
	}

}
