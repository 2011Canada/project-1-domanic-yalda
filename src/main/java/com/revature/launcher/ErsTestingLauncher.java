package com.revature.launcher;

import java.sql.Connection;

import com.revature.util.ConnectionFactory;

public class ErsTestingLauncher {

	public static void main(String[] args) {
		
		
		//test connection factory is working
		ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
		Connection conn = cf.getConnection();
		System.out.println("connected");
		
		
		
	}

}
