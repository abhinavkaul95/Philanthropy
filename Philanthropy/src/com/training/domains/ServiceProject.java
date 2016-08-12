/**
 * 
 */
package com.training.domains;
import com.training.enums.Available;
import java.util.*;

/**
 * @author akaul5
 *
 */
public class ServiceProject {
	private long projectID;
	private long projectCost;
	private double amountCollected;
	private double pendingAmount;
	private List<Donor> dnrList;
	private Available avail;
	
	
	
	/**
	 * 
	 */
	public ServiceProject() {
		super();
	}
	
	
	/**
	 * @param projectID
	 * @param projectCost
	 * @param amountCollected
	 * @param pendingAmount
	 * @param dnrList
	 * @param avail
	 */
	public ServiceProject(long projectID, long projectCost, double amountCollected, double pendingAmount, List<Donor> dnrList,
			Available avail) {
		super();
		this.projectID = projectID;
		this.projectCost = projectCost;
		this.amountCollected = amountCollected;
		this.pendingAmount = pendingAmount;
		this.dnrList = dnrList;
		this.avail = avail;
	}
	
	
	/**
	 * @return the projectID
	 */
	public long getProjectID() {
		return projectID;
	}


	/**
	 * @param projectID the projectID to set
	 */
	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}


	/**
	 * @return the projectCost
	 */
	public long getProjectCost() {
		return projectCost;
	}
	/**
	 * @param projectCost the projectCost to set
	 */
	public void setProjectCost(long projectCost) {
		this.projectCost = projectCost;
	}
	/**
	 * @return the amountCollected
	 */
	public double getAmountCollected() {
		return amountCollected;
	}
	/**
	 * @param amountCollected the amountCollected to set
	 */
	public void setAmountCollected(double amountCollected) {
		this.amountCollected = amountCollected;
	}
	/**
	 * @return the pendingAmount
	 */
	public double getPendingAmount() {
		return pendingAmount;
	}
	/**
	 * @param pendingAmount the pendingAmount to set
	 */
	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	/**
	 * @return the dnrList
	 */
	public List<Donor> getDnrList() {
		return dnrList;
	}
	/**
	 * @param dnrList the dnrList to set
	 */
	public void setDnrList(List<Donor> dnrList) {
		this.dnrList = dnrList;
	}
	/**
	 * @return the avail
	 */
	public Available getAvail() {
		return avail;
	}
	/**
	 * @param avail the avail to set
	 */
	public void setAvail() {
		this.avail = Available.AVAILABLE;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ServiceProject [projectID=" + projectID + ", projectCost=" + projectCost + ", amountCollected="
				+ amountCollected + ", pendingAmount=" + pendingAmount + ", dnrList=" + dnrList + ", avail=" + avail
				+ "]";
	}
	
	
	
	
	
	
}
