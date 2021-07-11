package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static final String dbDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql:// localhost:3306/";
	static final String dbName = "highdata";
	static final String dbUsername = "root";
	static final String dbPassword ="admin123";

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection con=null;
	    Statement stmt=null;
	    try {
	    	Class.forName(dbDriver);
			con = DriverManager.getConnection(dbURL + dbName,
						dbUsername, dbPassword);
			stmt=con.createStatement();
		    String sql;
		    sql= "SELECT name_customer,cust_number,invoice_id,due_in_date,total_open_amount,predicted_payment_date from mytable order by due_in_date desc limit 15";
		    ResultSet rs=stmt.executeQuery(sql);
		    
		    //response.setCharacterEncoding("UTF-8");
		    //int start=Integer.parseInt(request.getParameter("start"));
		    //int limit=Integer.parseInt(request.getParameter("limit"));
		    List<Response>invoicelist=new ArrayList<>();
		    //rs=retrievedata(start*limit,limit);
			while(rs.next())
		    {Response invoiceob=new Response();
		     invoiceob.setName_customer(rs.getString("name_customer"));
		     invoiceob.setCust_number(rs.getString("cust_number"));
		     
		     invoiceob.setInvoice_id(rs.getLong("invoice_id"));
		     invoiceob.setTotal_open_amount(rs.getInt("total_open_amount"));
		   
		     invoiceob.setDue_in_date(rs.getDate("due_in_date"));
		    invoiceob.setPredicted_payment_date(rs.getDate("predicted_payment_date"));
		    /*invoiceob.setnotes(rs.getString("notes"));*/
		    invoicelist.add(invoiceob);
			}
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json =gson.toJson(invoicelist);
			System.out.println(json);
			PrintWriter out = response.getWriter();
			out.print(json);
			 out.flush();
			
 
	   
	    
	    
	    }
	    catch(Exception ex) {
	    	ex.printStackTrace();
	    }
	
			
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		}
}
