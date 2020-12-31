package com.revature.exceptions;

public class InternalErrorException  extends AbstractHttpException{

	public InternalErrorException() {
		super("it broke", 500);
		// TODO Auto-generated constructor stub
	}

}
