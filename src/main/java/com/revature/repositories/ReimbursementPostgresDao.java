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

	public void UpdateStatus(Reimbursements r) {
		// TODO Auto-generated method stub
		
	}

	public Reimbursements addReimbursement(Reimbursements r) {
		// TODO Auto-generated method stub
		return null;
	}

}
