package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.ErrorController;
import com.revature.controllers.UserController;
import com.revature.exceptions.UnauthenticatedException;
import com.revature.exceptions.UnauthorizedException;

/**
 * Servlet implementation class ViewReimbursements
 */
public class ViewReimbursements extends HttpServlet {
       
	private ErrorController errorController = new ErrorController();
	
	private UserController userController = new UserController();
    
	protected void directControlRouter(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException, UnauthorizedException, UnauthenticatedException {
		//how to get a value from the init params
		System.out.println(this.getInitParameter("DefaultRole"));
		ServletContext sc = this.getServletContext();
		
		System.out.println(sc.getInitParameter("JavaCoolFactor"));
		
		//the front controller
		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
				req.getRequestURI().length());
		
		System.out.println(URI);
		switch (URI) {
			case "/employee-home": {
			switch (req.getMethod()) {
				case "GET":{
					res.setStatus(400);
					res.getWriter().write("Method Not Supported");
					break;
				}
				case "POST":{
					userController.viewUserReimbursements(req, res);
					break;
				}
				case "PUT":{
					res.setStatus(400);
					res.getWriter().write("Method Not Supported");
					break;
				}
				case "DELETE":{
					res.setStatus(400);
					res.getWriter().write("Method Not Supported");
					break;
				}
				default:{
					res.setStatus(400);
					res.getWriter().write("Method Not Supported");
					break;
				}
			}
			}
			default:{
				res.setStatus(404);
				res.getWriter().write("No Such Resource");
				break;
			
			}
		}
}

	protected void directControl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//to handle all internal errors/exceptions
		try {
			directControlRouter(request, response);
		}catch (Throwable t) {
			errorController.handle(request, response, t);//go to the error controller
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		directControl(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
