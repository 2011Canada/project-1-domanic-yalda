package com.revature.launcher;

import java.sql.Connection;

import com.revature.repositories.UserDao;
import com.revature.repositories.UserPostgresDao;
import com.revature.util.ConnectionFactory;

public class ErsTestingLauncher {

	public static void main(String[] args) {
		
		UserDao ud = new UserPostgresDao(); 
		
		//test connection factory is working
		ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
		Connection conn = cf.getConnection();
		System.out.println("connected");
		
		
		System.out.println(ud.findUserByUsernamePassword("dy", "1234"));
		
	}

}
