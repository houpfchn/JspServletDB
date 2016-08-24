package com.sevenun.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sevenun.model.Account;
import com.sevenun.util.DBUtil;

public class AccountDao {
	
	private Connection conn;
	
	public AccountDao() {
		conn = DBUtil.getConnection();
	}
	
	public void addAccount(Account acc) {
		PreparedStatement ps = null;
		String sql = "INSERT INTO accounts(profilePath, username,"
				+ "password, email) VALUES(?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, acc.getProfilePath());
			ps.setString(2,  acc.getUsername());
			ps.setString(3, acc.getPassword());
			ps.setString(4, acc.getEmail());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteAccount(int acc_id) {
		PreparedStatement ps = null;
		String sql = "DELETE FROM accounts WHERE acc_id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, acc_id);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateAccount(Account acc) {
		PreparedStatement ps = null;
		String sql = "UPDATE accounts SET profilePath=?, username=?, "
				+ "password=?, email=? WHERE acc_id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, acc.getProfilePath());
			ps.setString(2,  acc.getUsername());
			ps.setString(3, acc.getPassword());
			ps.setString(4, acc.getEmail());
			ps.setInt(5, acc.getAcc_id());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Account getAccountById(int acc_id) {
		Account account = new Account();
		PreparedStatement ps = null;
		String sql = "SELECT * FROM accounts WHERE acc_id=?";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, acc_id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				account.setAcc_id(rs.getInt("acc_id"));
				account.setProfilePath(rs.getString("profilePath"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return account;
	}
	
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<Account>();
		PreparedStatement ps = null;
		String sql = "SELECT * FROM accounts";
		
		try {
			ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setAcc_id(rs.getInt("acc_id"));
				account.setProfilePath(rs.getString("profilePath"));
				account.setUsername(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setEmail(rs.getString("email"));
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return accounts;
	}
	
}
