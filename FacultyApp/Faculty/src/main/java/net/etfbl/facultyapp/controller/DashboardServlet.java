package net.etfbl.facultyapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page == null) page = "home";
		
		if ("companies".equals(page)) {
	        request.getRequestDispatcher("/company")
	               .include(request, response);
	       
	    }
		if("internships".equals(page)) {
			request.getRequestDispatcher("/internships")
            .include(request, response);
		}
		
		if("students".equals(page)) {
			request.getRequestDispatcher("/students")
			.include(request, response);
		}
		if("worklogs".equals(page)) {
			request.getRequestDispatcher("/worklogs")
			.include(request, response);
		}
		
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
