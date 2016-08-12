/**
 * 
 */
package com.training.domains;

import java.sql.*;

import com.training.utils.DonorDAO;
import com.training.utils.MySQLConnection;
import com.training.utils.ProjectDAO;

/**
 * @author akaul5
 *
 */
	public class Donor {
	private long dnrId;
	private String name;
	private String emailId;
	private double amountDonated;
	/**
	 * @param dnrId
	 * @param name
	 * @param emailId
	 * @param amountDonated
	 */
	public Donor(long dnrId, String name, String emailId, double amountDonated) {
		super();
		this.dnrId = dnrId;
		this.name = name;
		this.emailId = emailId;
		this.amountDonated = amountDonated;
	}
	
	/**
	 * 
	 */
	public Donor() {
		super();
	}
	/**
	 * @return the dnrId
	 */
	public long getDnrId() {
		return dnrId;
	}

	/**
	 * @param dnrId the dnrId to set
	 */
	public void setDnrId(long dnrId) {
		this.dnrId = dnrId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the amountDonated
	 */
	public double getAmountDonated() {
		return amountDonated;
	}

	/**
	 * @param amountDonated the amountDonated to set
	 */
	public void setAmountDonated(double amountDonated) {
		this.amountDonated = amountDonated;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dnrId ^ (dnrId >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		if (dnrId != other.dnrId)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Donor [dnrId=" + dnrId + ", name=" + name + ", emailId=" + emailId + ", amountDonated=" + amountDonated
				+ "]";
	}
	// To be edited
	public void donate(double amount, ServiceProject proj){
		double newAmount = proj.getAmountCollected() + amount;
		proj.setAmountCollected(newAmount);
		this.setAmountDonated(this.getAmountDonated() + amount);
		Connection conn = MySQLConnection.getMyOracleConnection();
		String sql;
		DonorDAO dnrdao = new DonorDAO();
		ProjectDAO projdao = new ProjectDAO();
		PreparedStatement pstmt;
		try {
			sql = "insert into Dnr_Project values(" + dnrId + "," + proj.getProjectID() + ")";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			dnrdao.updateAmount(dnrId, amount);
			projdao.updateAmount(proj.getProjectID(), amount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
