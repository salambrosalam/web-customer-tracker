package com.salambrosalam.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public TestDbServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//setup connection veriable
		
		String user = "springstudent";
		String pass = "springstudent";
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try { 
			
			PrintWriter out = response.getWriter();
			
			out.println("Connection to database:" + jdbcUrl);
			
			Class.forName(driver);
			
			Connection myCon = DriverManager.getConnection(jdbcUrl,user,pass);
			
			out.println("You are good hacker bro)");
			
			myCon.close();
			
		}catch (Exception exc) {
			exc.printStackTrace();
			throw new ServletException(exc);
		}
		
		
		
		//get connection to database
		
		
		
		
		
		
		
	}

}
