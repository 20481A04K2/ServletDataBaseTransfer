package com.projectDaoClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.projectBeanClass.User;

public class Dao {
	String driverclasss="com.mysql.cj.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/palleinstitute";
	String user="root";
	String pass="root";
	Connection con=null;
	PreparedStatement pst=null;
	Statement s=null;
	String query="insert into user(name,email,password) values(?,?,?);";
	String fetch="select * from user";
	boolean  isDataInserted=true;
	
	public boolean insert(User uc) {
		try {
//			Load classs
			Class.forName(driverclasss);
			
//			establish Connection
			con=DriverManager.getConnection(url,user,pass);
			
			Statement s = con.createStatement();
			
			
//			PreparedStatement
			ResultSet rs=s.executeQuery("select * from user");
			while(rs.next()) {
				if(uc.getEmail().equals(rs.getString("email"))) {// using this to know the data already exist or not
					
					isDataInserted=false;// if it is exist it not add the data into the database and terminate the loop
					break;
				}
			}
			
			if(isDataInserted==true) {
				
			pst=con.prepareStatement(query);
			pst.setString(1,uc.getName());
			pst.setString(2,uc.getEmail());
			pst.setString(3, uc.getPassword());
			pst.executeUpdate();
			
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(pst!=null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		return isDataInserted;
	}
	public boolean validUser(User u) {
		
		boolean isDataPresent=false;
		try {
//			load driver 
			Class.forName(driverclasss);
			
//			establish Connection
			con=DriverManager.getConnection(url,user,pass);

//			Statement
			s=con.createStatement();
			ResultSet rs=s.executeQuery(fetch);
			while(rs.next()) {
				if(u.getEmail().equals(rs.getString("email")) && u.getPassword().equals(rs.getNString("password"))) {// data is matched returns true otherwise false
					isDataPresent=true;// the data is present it allow the next page other wise it wont allow
					break;
					
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		

		
		
	  }
		return isDataPresent;
  }
	
}
