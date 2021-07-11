package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.Date;
import java.util.ArrayList;
//import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class NewwalaServlet
 */
@WebServlet("/NewwalaServlet")
public class NewwalaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final String dbDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql:// localhost:3306/";
	static final String dbName = "highdata";
	static final String dbUsername = "root";
	static final String dbPassword ="admin123";
	//private static final ServletResponse res = null;
	public static ResultSet retrievedata(int start,int limit)throws Exception{
    	
		//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			Connection con=null;
		    PreparedStatement stmt=null;
		    String sql=null;
		    ResultSet rs=null;
		    //List<Response>responseList=new ArrayList<>();
		    
		    try{
		    	Class.forName(dbDriver).newInstance();
				con = DriverManager.getConnection(dbURL + dbName,dbUsername, dbPassword);
				//stmt=con.createStatement();
				sql= "SELECT cust_number,name_customer,invoice_id,due_in_date,total_open_amount,predicted_payment_date from mytable limit ?,?";
				stmt=con.prepareStatement(sql);
				stmt.setInt(1,start);
				stmt.setInt(2,limit);
				rs=stmt.executeQuery();
		    }catch(Exception ex) {
		    	ex.printStackTrace();
		    }
			return rs;
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public NewwalaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
	    response.setCharacterEncoding("UTF-8");
	    int start=Integer.parseInt(request.getParameter("start"));
	    int limit=Integer.parseInt(request.getParameter("limit"));
	    ArrayList<Response>record=new ArrayList<>();
	    ResultSet rs;
		try{
			rs=retrievedata(start*limit,limit);
			while(rs.next()) {
				
				Response invoiceResponse=new Response();
				invoiceResponse.setName_customer(rs.getString("name_customer"));
				invoiceResponse.setCust_number(rs.getString("cust_number"));
			     
				invoiceResponse.setInvoice_id(rs.getLong("invoice_id"));
				invoiceResponse.setTotal_open_amount(rs.getInt("total_open_amount"));
			   
				invoiceResponse.setDue_in_date(rs.getDate("due_in_date"));
				invoiceResponse.setPredicted_payment_date(rs.getDate("predicted_payment_date"));
			    /*invoiceob.setnotes(rs.getString("notes"));*/
			    record.add(invoiceResponse);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		Gson gson=new Gson();
		String data=gson.toJson(record);
		PrintWriter out=response.getWriter();
		out.print(data);
		System.out.println("Sucessfully done");
		out.flush();
		
				
			
			//Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//String json =gson.toJson(responseList);
			//System.out.println(json);
			//response.setContentType("application/json");
			//response.getWriter().write(json);
				
			
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
