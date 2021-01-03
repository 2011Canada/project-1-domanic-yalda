package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserPostgresDao implements UserDao {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();


	public UserPostgresDao() {
		super();
	}


	public List<User> findAllUsers() throws UserNotFoundException, InternalErrorException{
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_users\";";
			PreparedStatement getUser = conn.prepareStatement(sql);
			
			ResultSet res = getUser.executeQuery();
			List<User> allUsers = new ArrayList<User>();
			while(res.next()) {
				User u = new User(res.getString("ers_username"), res.getString("ers_password"), res.getString("user_first_name"), res.getString("user_last_name"), res.getString("user_email"), res.getString("user_role_id"));	
				allUsers.add(u);
			}
			return allUsers;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	

	public User findUserByUsernamePassword(String username, String password) throws UserNotFoundException, InternalErrorException{
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_users\" where \"ers_username\" = ? and \"ers_password\" = ? ;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			getUser.setString(1, username);
			getUser.setString(2, password);
			
			ResultSet res = getUser.executeQuery();
			if(res.next()) {
				User u = new User(res.getString("ers_username"), res.getString("ers_password"), res.getString("user_first_name"), 
						res.getString("user_last_name"), res.getString("user_email"), res.getString("user_role"));	
				return u;
			}else {
				throw new UserNotFoundException();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}

	// is it an employee or a finance user
	public List<User> findAllEmployees() throws UserNotFoundException, InternalErrorException {
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_users\" where \"user_type\" = employee ;";
			PreparedStatement getUser = conn.prepareStatement(sql);
			
			ResultSet res = getUser.executeQuery();
			List<User> allUsersOfType = new ArrayList<User>();
			while(res.next()) {
				User u = new User(res.getString("ers_username"), res.getString("ers_password"), res.getString("user_first_name"), res.getString("user_last_name"), res.getString("user_email"), res.getString("user_role"));	
				allUsersOfType.add(u);
			}return allUsersOfType;	
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	
	}
}

