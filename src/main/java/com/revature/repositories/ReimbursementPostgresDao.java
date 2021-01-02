package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.models.Reimbursements;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class ReimbursementPostgresDao implements ReimbursementDao{

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	//this is for the manager
	public List<Reimbursements> findAllReimbursements() throws InternalErrorException, ReimbursementNotFoundException{
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_reimbursement\" er left join \"ers_users\" eu on er.reimb_author = eu.ers_users_id;";
			PreparedStatement getTickets = conn.prepareStatement(sql);
			
			ResultSet res = getTickets.executeQuery();
			List<Reimbursements> allTickets = new ArrayList<Reimbursements>();
			while(res.next()) {
				Reimbursements r = new Reimbursements(res.getDouble("reimb_amount"), res.getString("reimb_type"), res.getDate("reimb_submitted"), 
						res.getDate("reimb_resolved"), res.getString("reimb_status"), res.getString("reimb_description"), res.getInt("ers_user_id"));	
				allTickets.add(r);
			}
			return allTickets;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	//this is for the employee to find by employee id
	public List<Reimbursements> findAllReimbursementsByUserId(User u) throws InternalErrorException, ReimbursementNotFoundException{
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_reimbursement\" where \"reimb_author\" = " + u.getUserId() + ";";
			PreparedStatement getTickets = conn.prepareStatement(sql);
			
			ResultSet res = getTickets.executeQuery();
			List<Reimbursements> allTickets = new ArrayList<Reimbursements>();
			while(res.next()) {
				Reimbursements r = new Reimbursements(res.getDouble("reimb_amount"), res.getString("reimb_type"), res.getDate("reimb_submitted"), 
						res.getDate("reimb_resolved"), res.getString("reimb_status"), res.getString("reimb_description"), res.getInt("ers_user_id"));	
				allTickets.add(r);
			}
			return allTickets;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	public List<Reimbursements> findAllReimbursementsByStatusApproved() throws InternalErrorException, ReimbursementNotFoundException{
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_reimbursement\" where \"reimb_status\" = 'APPROVED' ;";
			PreparedStatement getTickets = conn.prepareStatement(sql);
			
			ResultSet res = getTickets.executeQuery();
			List<Reimbursements> allTickets = new ArrayList<Reimbursements>();
			while(res.next()) {
				Reimbursements r = new Reimbursements(res.getDouble("reimb_amount"), res.getString("reimb_type"), res.getDate("reimb_submitted"), 
						res.getDate("reimb_resolved"), res.getString("reimb_status"), res.getString("reimb_description"), res.getInt("ers_user_id"));	
				allTickets.add(r);
			}
			return allTickets;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	public List<Reimbursements> findAllReimbursementsByStatusDenied() throws InternalErrorException, ReimbursementNotFoundException{
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_reimbursement\" where \"reimb_status\" = 'DENIED' ;";
			PreparedStatement getTickets = conn.prepareStatement(sql);
			
			ResultSet res = getTickets.executeQuery();
			List<Reimbursements> allTickets = new ArrayList<Reimbursements>();
			while(res.next()) {
				Reimbursements r = new Reimbursements(res.getDouble("reimb_amount"), res.getString("reimb_type"), res.getDate("reimb_submitted"), 
						res.getDate("reimb_resolved"), res.getString("reimb_status"), res.getString("reimb_description"), res.getInt("ers_user_id"));	
				allTickets.add(r);
			}
			return allTickets;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	public List<Reimbursements> findAllReimbursementsByStatusPending() throws InternalErrorException, ReimbursementNotFoundException{
		
		Connection conn = cf.getConnection();
		try {
			String sql = "select * from \"ers_reimbursement\" where \"reimb_status\" = 'PENDING' ;";
			PreparedStatement getTickets = conn.prepareStatement(sql);
			
			ResultSet res = getTickets.executeQuery();
			List<Reimbursements> allTickets = new ArrayList<Reimbursements>();
			while(res.next()) {
				Reimbursements r = new Reimbursements(res.getDouble("reimb_amount"), res.getString("reimb_type"), res.getDate("reimb_submitted"), 
						res.getDate("reimb_resolved"), res.getString("reimb_status"), res.getString("reimb_description"), res.getInt("ers_user_id"));	
				allTickets.add(r);
			}
			return allTickets;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InternalErrorException();
		} finally {
			cf.releaseConnection(conn);
		}
	}
	
	//manager approval of claim status change on the claim
	public void UpdateStatusApproved(Reimbursements r) {
		Connection conn = cf.getConnection();
		try {
			
			//inserting SQL statement
			String updateReimbursementStatusSQL = "update \"ers_reimbursement\" "
					+ "set \"reimb_status\" = 'APPROVED' , \"reimb_resolver\" = 1, \"reimb_resolved\" = " + r.getTime()
					+ " where \"reimb_author\"= " + r.getClaimOwner();
			PreparedStatement updateReimbursementStatus = conn.prepareStatement(updateReimbursementStatusSQL);
			
			updateReimbursementStatus.setString(1, "APPROVED");

			updateReimbursementStatus.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			cf.releaseConnection(conn);
		}
	}
	//manager denial of claim status change
	public void UpdateStatusDenied(Reimbursements r) {
		Connection conn = cf.getConnection();
		try {
			//inserting SQL statement
			String updateReimbursementStatusSQL = "update \"ers_reimbursement\" "
					+ "set \"reimb_status\" = 'DENIED' , \"reimb_resolver\" = 1, \"reimb_resolved\" = " + r.getTime()
					+ " where \"reimb_author\"= " + r.getClaimOwner();
			PreparedStatement updateReimbursementStatus = conn.prepareStatement(updateReimbursementStatusSQL);
			
			updateReimbursementStatus.setString(1, "DENIED");

			updateReimbursementStatus.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally{
			cf.releaseConnection(conn);
		}
		
	}

	public Reimbursements addReimbursement( User u) {
		/*Connection conn = cf.getConnection();
		try {
			conn.setAutoCommit(false);
			
			//inserting SQL statement
			String reimbursementSQL = "insert into \"ers_reimbursement\" "
					+ "(\"reimb_amount\", \"reimb_submitted\", \"reim_resolved\", \"reimb_description\", \"reim_status\", \"reim_type\" )"
					+ "values (?,?,?,?,?,?);";
			PreparedStatement insertReimbursement = conn.prepareStatement(reimbursementSQL);
			
			insertReimbursement.setString(1, c.getFirstName());
			insertReimbursement.setString(2, c.getLastName());
			insertReimbursement.setString(3, c.getUsername());
			insertReimbursement.setString(4, c.getPassword());
			insertReimbursement.setInt(5, (u.getUserId()));
			
			ResultSet res = insertReimbursement.executeQuery();
			
			if( c.getBankAccount() != null ) {
				bapd.addAccount(c, c.getBankAccount());
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}finally {	
			try {
				conn.setAutoCommit(true);
			}catch(SQLException e){
				e.printStackTrace();
			}
			cf.releaseConnection(conn);
		}
		*/
		Reimbursements r = new Reimbursements();
		return r;
	}
	

}
