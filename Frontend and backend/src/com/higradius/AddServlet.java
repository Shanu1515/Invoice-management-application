package com.higradius;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = 1L;
	static final String dbDriver = "com.mysql.jdbc.Driver";
	static final String dbURL = "jdbc:mysql:// localhost:3306/";
	static final String dbName = "highdata";
	static final String dbUsername = "root";
	static final String dbPassword ="admin123";
    
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw;
		response.setContentType("text/html");
		pw=response.getWriter();
		String name_customer=request.getParameter("name_customer");
		
	    String cust_number=request.getParameter("cust_number");
		//private String name_customer;
	    String invoice_id1=request.getParameter("invoice_id");
		String total_open_amount1=request.getParameter("total_open_amount");
		String due_in_date1=request.getParameter("due_in_date");
		//String predicted_payment_date=request.getParameter("predicted_payment_date");
		long invoice_id=Integer.parseInt(invoice_id1);
		int total_open_amount=Integer.parseInt(total_open_amount1);
		//SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		
		Date due_in_date=Date.valueOf(due_in_date1);
		
		
		//int executionStatus=0;
		Connection con=null;
		PreparedStatement stmt=null;
		//List<ExecutionResponse>demoList=new ArrayList<>();
		//String jsonString=null;
	//ExecutionResponse demo=new ExecutionResponse();
		
		try {
			Class.forName(dbDriver).newInstance();
			con = DriverManager.getConnection(dbURL + dbName,dbUsername, dbPassword);
			//stmt=con.createStatement();
			String sql="insert into mytable(name_customer,cust_number,invoice_id,due_in_date,total_open_amount)values(?,?,?,?,?)";
			stmt= con.prepareStatement(sql);
			
			stmt.setString(1,name_customer);
			stmt.setString(2,cust_number);
			stmt.setLong(3,invoice_id);
			stmt.setDate(4,due_in_date);
			stmt.setInt(5,total_open_amount);
			
			int x=stmt.executeUpdate();
			if(x==1)
			{
				pw.println("Values inserted");
			}
			
			//executionStatus=stmt.executeUpdate();
			//if(executionStatus>=1) {
				//demo.setExecutionStatus("true");
				//demo.setExecutionMessage("Data inserted successfully");
			//}else {
				//demo.setExecutionStatus("false");
				//demo.setExecutionMessage("Failed to insert");
			//}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		pw.close();
		
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
