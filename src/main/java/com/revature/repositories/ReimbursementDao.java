package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.models.Reimbursements;
import com.revature.models.User;

public interface ReimbursementDao {

	public List<Reimbursements> findAllReimbursements() throws InternalErrorException, ReimbursementNotFoundException;
	
	public List<Reimbursements> findAllReimbursementsByUserId(User u) throws InternalErrorException, ReimbursementNotFoundException;
	
	public List<Reimbursements> findAllReimbursementsByStatusApproved() throws InternalErrorException, ReimbursementNotFoundException;
	public List<Reimbursements> findAllReimbursementsByStatusDenied() throws InternalErrorException, ReimbursementNotFoundException;
	public List<Reimbursements> findAllReimbursementsByStatusPending() throws InternalErrorException, ReimbursementNotFoundException;
	
	public void UpdateStatusApproved(Reimbursements r);
	public void UpdateStatusDenied(Reimbursements r);
	
	public Reimbursements addReimbursement( User u);

}
