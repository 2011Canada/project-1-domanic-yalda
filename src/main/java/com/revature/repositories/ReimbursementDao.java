package com.revature.repositories;

import java.util.List;

import com.revature.exceptions.InternalErrorException;
import com.revature.exceptions.ReimbursementNotFoundException;
import com.revature.models.Reimbursements;

public interface ReimbursementDao {

	public List<Reimbursements> findAllReimbursements() throws InternalErrorException, ReimbursementNotFoundException;
	
	public void UpdateStatus(Reimbursements r);
	
	public Reimbursements addReimbursement(Reimbursements r);

}
