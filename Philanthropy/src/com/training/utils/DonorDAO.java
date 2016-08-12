package com.training.utils;

import java.sql.*;
import java.util.*;

import com.training.ifaces.InterfaceMyDAO;
import com.training.domains.Donor;

public class DonorDAO implements InterfaceMyDAO<Donor> {
	private Connection conn = null;
	
	/**
	 * 
	 */
	public DonorDAO() {
		super();
		conn = MySQLConnection.getMyOracleConnection();
	}

	/**
	 * @param conn
	 */
	public DonorDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	@Override
	public int add(Donor t) {
		int rowAdded = 0;
		String sql = "insert into Donor values(?,?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, t.getDnrId());
			pstmt.setString(2, t.getName());
			pstmt.setString(3, t.getEmailId());
			pstmt.setDouble(4, t.getAmountDonated());
			rowAdded = pstmt.executeUpdate();
		} catch (SQLException e) {		
			e.printStackTrace();
		}

		return rowAdded;
	}

	@Override
	public int update(long dnrId, String... strings) {
		int rowUpdated = 0;
		String sql = "update Donor set name=?,emailId=?,amount=? where dnrId = " + dnrId;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strings[0]);
			pstmt.setString(2, strings[1]);
			pstmt.setLong(3, Long.parseLong(strings[2]));
			rowUpdated = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
	@Override
	public int updateAmount(long dnrId, double amount) {
		int rowUpdated = 0;
		String sql_fetch = "select amount from donor where dnrId = " + dnrId;
		String sql_update = "update donor set amount=? where dnrId = " + dnrId;
		try {
			PreparedStatement pstmt_fetch = conn.prepareStatement(sql_fetch);
			ResultSet resultSet_fetch = pstmt_fetch.executeQuery();
			double oldAmount = 0;
			while(resultSet_fetch.next()){
			oldAmount = resultSet_fetch.getDouble("amount");
			}
			PreparedStatement pstmt_update = conn.prepareStatement(sql_update);
			pstmt_update.setDouble(1, oldAmount + amount);
			rowUpdated = pstmt_update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowUpdated;
	}
	
	@Override
	public Donor find(long dnrId) {
		ResultSet resultSet;
		String sql = "select * from Donor where dnrId = " + dnrId;
		String name = null;
		String email = null;
		long amount = 0;
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			if(resultSet.next()){
			name = resultSet.getString("name");
			email = resultSet.getString("emailId");
			amount = resultSet.getLong("amount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new Donor(dnrId, name, email, amount);
	}

	@Override
	public int delete(long dnrId) {
		int result = 0;
		CallableStatement callstmt;
		try {
			callstmt = conn.prepareCall("{call delete_record(?)}");
			callstmt.setLong(1,dnrId);
			result = callstmt.executeUpdate();
			// To be done

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	@Override
	public List<Donor> findAll(long dnrId) {
		ResultSet resultSet;
		List<Donor> list = new ArrayList<Donor>();
		String sql = "select * from Donor where dnrId = " + dnrId;
		String name = null;
		String email = null;
		long amount = 0;
		
		Statement stmt;
		try {
			stmt = conn.createStatement();
			resultSet = stmt.executeQuery(sql);
			while(resultSet.next()){
			name = resultSet.getString("name");
			email = resultSet.getString("emailId");
			amount = resultSet.getLong("amount");
			list.add(new Donor(dnrId, name, email, amount));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
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
