package com.miniprojectservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectBeanClass.User;
import com.projectDaoClass.Dao;



@WebServlet("/")
public class MiniProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String path = request.getServletPath();//this method will give you the servlet path
	  switch(path) {
	  case "/reg":
		  registerUser(request,response);
		  break;
	  case "/login":
		  validateUser(request,response);
		  break;
	  case "/studenthome":
		  String action=request.getParameter("action");
		  switch(action) {
		  case "Add Student":
			  addStudent(request,response);
			  break;
		  case "Display":
			  break;
		  case "Update":
			  break;
		  case "Delete":
			  break;
		  }
		  break;
		  
	  }
	}
		 




	private void addStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("StudentForm.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	private void validateUser(HttpServletRequest request, HttpServletResponse response) {
		String username=request.getParameter("lemail");
		String password=request.getParameter("lpass");
		User u=new User(username,password);
		Dao d= new Dao();
		boolean isDataPresent=d.validUser(u);
		if(isDataPresent) {
		try {
			response.sendRedirect("home.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else {
			request.setAttribute("message","User doesn't Exist");
			RequestDispatcher rd=request.getRequestDispatcher("login.jsp");// with help of of it send the and shows the error
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	private void registerUser(HttpServletRequest request, HttpServletResponse response)  {
		// TODO Auto-generated method stub
		 String name =request.getParameter("tbname");
		  String email=request.getParameter("tbmail");
		  String password=request.getParameter("tbpass");
		  User uc=new User(name,email,password);
		  Dao d=new Dao();
		 boolean isDataInserted= d.insert(uc);
		 response.setContentType("text/html");
		 PrintWriter out;
		 try {
			 out=response.getWriter();
			 if(isDataInserted) {
					
					response.sendRedirect("login.jsp");
				 }
				 else {
					 request.setAttribute("message","The email id is already exist..");
					 RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
					 try {
						rd.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				 }
				 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	
	}
}
