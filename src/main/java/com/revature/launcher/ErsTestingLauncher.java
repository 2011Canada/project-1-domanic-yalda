package com.revature.launcher;

import java.sql.Connection;

import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementPostgresDao;
import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgresDao;
import com.revature.services.UserServices;
import com.revature.services.UserServicesImplementation;
import com.revature.util.ConnectionFactory;

public class ErsTestingLauncher {

	public static void main(String[] args) {
		
		UserDao ud = new UserPostgresDao(); 
		ReimbursementDao rd = new ReimbursementPostgresDao();
		
		//test connection factory is working
		ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
		Connection conn = cf.getConnection();
		System.out.println("connected");
		
		
		System.out.println(ud.findUserByUsernamePassword("dy", "1234"));
		System.out.println(ud.findAllEmployees());
		System.out.println(ud.findAllUsers());
		System.out.println(rd.findAllReimbursements());
		System.out.println("");
		
	}

}
