package com.higradius;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;  

public class DatabaseConnection {
	static final String dbDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql:// localhost:3306/";
	static final String dbName = "highdata";
	static final String dbUsername = "root";
	static final String dbPassword ="admin123";
	public static void main(String[] args){
		Connection con=null;
	    Statement stmt=null;
	    try{
	    	Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL + dbName,
						dbUsername, dbPassword);
			stmt=con.createStatement();
		    String sql;
		    sql="SELECT delay_days,business_code,name_customer,invoice_currency from mytable";
			ResultSet rse=stmt.executeQuery(sql);
		    while(rse.next())
		    {
		    	int delay_days=rse.getInt("delay_days");
				String business_code=rse.getString("business_code");
				String name_customer=rse.getString("name_customer");
				String invoice_currency=rse.getString("invoice_currency");
				    	
				System.out.print("Delay: "+delay_days);
				System.out.print("Code: "+business_code);
				System.out.print("Name: "+name_customer);
				System.out.println("Currency: "+invoice_currency);
			}
		    rse.close();
		    stmt.close();
		    con.close();
	    }catch(SQLException se) {
	    	se.printStackTrace();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
	    		if(stmt!=null)
	    			stmt.close();
	    		}catch(SQLException se2) {
			    }
				 try {
					 if(con!=null)
						 con.close();
				 }catch(SQLException se) {
					 se.printStackTrace();
				 }
				}
				System.out.println("Bye");
				}
				
				
				
				    
				
			

}
