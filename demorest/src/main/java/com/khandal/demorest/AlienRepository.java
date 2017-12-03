package com.khandal.demorest;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class AlienRepository {
	
	

	Connection con=null;
	
	public AlienRepository() {
		// TODO Auto-generated constructor stub
		
		String url="jdbc:mysql://localhost:3306/restdb";
		String userName="root";
		String password="root";
		
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, userName, password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Alien> getAliens(){
				
		List<Alien> aliens= new ArrayList<Alien>();
		String sql="select * from alien";
		
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			
			while(rs.next()) {
				Alien a= new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return aliens;
	}
	
	public Alien getAlien(int id) {
		
	
		String sql="select * from alien where id="+id;
		Alien a= new Alien();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(sql);
			
			if(rs.next()) {
				
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return a;
	}

	public void create(Alien a) {
		// TODO Auto-generated method stub
		String sql="insert into alien values(?,?,?)";
		
		
		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1,a.getId());
			pStmt.setString(2,a.getName());
			pStmt.setInt(3,a.getPoints());
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Alien a) {
		// TODO Auto-generated method stub
		String sql="update alien SET name=?,points=? where id=?";
		
		
		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1,a.getName());
			pStmt.setInt(2,a.getPoints());
			pStmt.setInt(3,a.getId());
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from alien where id=?";
		
		
		try {
			PreparedStatement pStmt = con.prepareStatement(sql);
			pStmt.setInt(1,id);
			pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
