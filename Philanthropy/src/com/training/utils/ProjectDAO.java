package com.training.utils;

import java.sql.*;
import java.util.*;

import com.training.domains.Donor;
import com.training.domains.ServiceProject;
import com.training.enums.Available;
import com.training.ifaces.InterfaceMyDAO;

public class ProjectDAO implements InterfaceMyDAO<ServiceProject>{
	private Connection conn = null;
	
	/**
	 * 
	 */
	public ProjectDAO() {
		super();
		conn = MySQLConnection.getMyOracleConnection();
	}

	/**
	 * @param conn
	 */
	public ProjectDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public int add(ServiceProject proj) {
		int rowAdded = 0;
		String sql = "insert into ServiceProject values(?,?,?,?,0)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, proj.getProjectID());
			pstmt.setLong(2, proj.getProjectCost());
			pstmt.setDouble(3, proj.getAmountCollected());
			pstmt.setDouble(4, proj.getPendingAmount());
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowAdded;
	}

	@Override
	public int update(long projectId, String... strings) {
		int rowUpdated = 0;
		String sql = "update ServiceProject set projectCost=?,amountCollected=?, pendingAmount=?, avail=1 where projectId = " + projectId;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, Long.parseLong(strings[0]));
			pstmt.setLong(2, Long.parseLong(strings[1]));
			pstmt.setLong(3, Long.parseLong(strings[2]));
			rowUpdated = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
	@Override
	public int updateAmount(long projectId, double amount) {
		int rowUpdated = 0;
		String sql_fetch = "select amountCollected from serviceproject where projectId = " + projectId;
		String sql_update = "update ServiceProject set amountCollected=? where projectId = " + projectId;
		try {
			PreparedStatement pstmt_fetch = conn.prepareStatement(sql_fetch);
			ResultSet resultSet_fetch = pstmt_fetch.executeQuery();
			double oldAmount = 0;
			while(resultSet_fetch.next()){
			oldAmount = resultSet_fetch.getDouble("amountCollected");
			}
			PreparedStatement pstmt_update = conn.prepareStatement(sql_update);
			pstmt_update.setDouble(1, oldAmount + amount);
			rowUpdated = pstmt_update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
	public boolean unsetAvailability(long projectId) {
		boolean result = false;
		String sql = "update ServiceProject set avail=?  where projectId = " + projectId;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.execute(sql);
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public ServiceProject find(long projectId) {
		long projectCost = 0;
		double amountCollected = 0;
		double pendingAmount = 0;
		long dnrId = 0;
		String name = null;
		String emailId = null;
		double amountDonated = 0;
		List<Donor> donorlist = new ArrayList<Donor>();
		try {
			Statement stmt_project = conn.createStatement();
			String sql_project = "select * from ServiceProject where projectID = " + projectId;
			ResultSet resultSet_project = stmt_project.executeQuery(sql_project);
			Statement stmt_association = conn.createStatement();
			String sql_association = "select dnrId from dnr_project where projectID = " + projectId;
			ResultSet resultSet_association = stmt_association.executeQuery(sql_association);
			Statement stmt_donor = conn.createStatement();
			String sql_donor = "select * from donor where dnrId = ";
			ResultSet resultSet_donor;
		
			if(resultSet_project.next()){
				projectCost = resultSet_project.getLong("projectCost");
				amountCollected = resultSet_project.getLong("amountCollected");
				pendingAmount = resultSet_project.getLong("pendingAmount");
			while(resultSet_association.next()){
				dnrId = resultSet_association.getLong("dnrId");
				resultSet_donor =  stmt_donor.executeQuery(sql_donor + dnrId);
				while(resultSet_donor.next()){
					name = resultSet_donor.getString("name");
					emailId = resultSet_donor.getString("emailId");
					amountDonated = resultSet_donor.getLong("amount");
					Donor dnr = new Donor(dnrId, name, emailId, amountDonated);
					donorlist.add(dnr);
				}
				
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ServiceProject(projectId, projectCost, amountCollected, pendingAmount,donorlist,Available.AVAILABLE);
	}
	
	
	
	@Override
	public int delete(long projectId) {
		int result = 0;
		CallableStatement callstmt;
		try {
			callstmt = conn.prepareCall("{call delete_project(?)}");
			callstmt.setLong(1,projectId);
			result = callstmt.executeUpdate();
			// To be done

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<ServiceProject> findAll(long projectId) {
		long projectCost = 0;
		double amountCollected = 0;
		double pendingAmount = 0;
		long dnrId = 0;
		String name = null;
		String emailId = null;
		double amountDonated = 0;
		List<Donor> donorlist = new ArrayList<Donor>();
		List<ServiceProject> projectlist = new ArrayList<ServiceProject>();
		try {
		Statement stmt_project = conn.createStatement();
		String sql_project = "select * from ServiceProject where projectID = " + projectId;
		ResultSet resultSet_project = stmt_project.executeQuery(sql_project);
		Statement stmt_association = conn.createStatement();
		String sql_association = "select dnrId from dnr_project where projectID = " + projectId;
		ResultSet resultSet_association = stmt_association.executeQuery(sql_association);
		Statement stmt_donor = conn.createStatement();
		String sql_donor = "select * from donor where dnrId = ";
		ResultSet resultSet_donor;
		// A bit of problem that all donors are printed for all projects
			while(resultSet_project.next()){
				projectCost = resultSet_project.getLong("projectCost");
				amountCollected = resultSet_project.getLong("amountCollected");
				pendingAmount = resultSet_project.getLong("pendingAmount");
			while(resultSet_association.next()){
			
				dnrId = resultSet_association.getLong("dnrId");
				resultSet_donor =  stmt_donor.executeQuery(sql_donor + dnrId);
				while(resultSet_donor.next()){
					name = resultSet_donor.getString("name");
					emailId = resultSet_donor.getString("emailId");
					amountDonated = resultSet_donor.getLong("amount");
					Donor dnr = new Donor(dnrId, name, emailId, amountDonated);
					donorlist.add(dnr);
				}
				
			}
			ServiceProject proj = new ServiceProject(projectId, projectCost, amountCollected, pendingAmount,donorlist,Available.AVAILABLE);
			projectlist.add(proj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectlist;
	}
	
	public List<ServiceProject> findAll() {
		long projectID = 0;
		List<ServiceProject> projectlist = new ArrayList<ServiceProject>();
		ProjectDAO prodao = new ProjectDAO();
		try {
		Statement stmt = conn.createStatement();
		String sql = "select projectId from ServiceProject";
		ResultSet resultSet_project = stmt.executeQuery(sql);
			while(resultSet_project.next()){
				projectID = resultSet_project.getLong("projectId");
				projectlist.addAll(prodao.findAll(projectID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectlist;
	}

	@Override
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
