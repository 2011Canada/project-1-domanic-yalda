package com.revature.models;

import java.sql.Date;

public class Reimbursements {
	
	private double reimbursementAmount;
	private String reimbursementType;
	private Date reimbursementSubmission;
	private Date reimbursementResolution;
	private String reimbursementStatus;
	private String reimbursementDescription;
	private int claimOwner;


	public Reimbursements() {
		super();
	}

	public Reimbursements(double reimbursementAmount, String reimbursementType, Date reimbursementSubmission,
			Date reimbursementResolution, String reimbursementStatus, String reimbursementDescription, int claimOwner) {
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementType = reimbursementType;
		this.reimbursementSubmission = reimbursementSubmission;
		this.reimbursementResolution = reimbursementResolution;
		this.reimbursementStatus = reimbursementStatus;
		this.reimbursementDescription = reimbursementDescription;
		this.claimOwner = claimOwner;
	}
	
	
	public double getReimbursementAmount() {
		return reimbursementAmount;
	}
	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}
	public String getReimbursementType() {
		return reimbursementType;
	}
	public void setReimbursementType(String reimbursementType) {
		this.reimbursementType = reimbursementType;
	}
	public Date getReimbursementSubmission() {
		return reimbursementSubmission;
	}
	public void setReimbursementSubmission(Date reimbursementSubmission) {
		this.reimbursementSubmission = reimbursementSubmission;
	}
	public Date getReimbursementResolution() {
		return reimbursementResolution;
	}
	public void setReimbursementResolution(Date reimbursementResolution) {
		this.reimbursementResolution = reimbursementResolution;
	}
	public String getReimbursementStatus() {
		return reimbursementStatus;
	}
	public void setReimbursementStatus(String reimbursementStatus) {
		this.reimbursementStatus = reimbursementStatus;
	}
	public String getReimbursementDescription() {
		return reimbursementDescription;
	}
	public void setReimbursementDescription(String reimbursementDescription) {
		this.reimbursementDescription = reimbursementDescription;
	}
	public int getClaimOwner() {
		return claimOwner;
	}
	
	@Override
	public String toString() {
		return "Reimbursements [reimbursementAmount=" + reimbursementAmount + ", reimbursementType=" + reimbursementType
				+ ", reimbursementSubmission=" + reimbursementSubmission + ", reimbursementResolution="
				+ reimbursementResolution + ", reimbursementStatus=" + reimbursementStatus
				+ ", reimbursementDescription=" + reimbursementDescription + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursements other = (Reimbursements) obj;
		if (reimbursementAmount != other.reimbursementAmount)
			return false;
		if (reimbursementDescription == null) {
			if (other.reimbursementDescription != null)
				return false;
		} else if (!reimbursementDescription.equals(other.reimbursementDescription))
			return false;
		if (reimbursementResolution == null) {
			if (other.reimbursementResolution != null)
				return false;
		} else if (!reimbursementResolution.equals(other.reimbursementResolution))
			return false;
		if (reimbursementStatus == null) {
			if (other.reimbursementStatus != null)
				return false;
		} else if (!reimbursementStatus.equals(other.reimbursementStatus))
			return false;
		if (reimbursementSubmission == null) {
			if (other.reimbursementSubmission != null)
				return false;
		} else if (!reimbursementSubmission.equals(other.reimbursementSubmission))
			return false;
		if (reimbursementType == null) {
			if (other.reimbursementType != null)
				return false;
		} else if (!reimbursementType.equals(other.reimbursementType))
			return false;
		return true;
	}

	
	
}
