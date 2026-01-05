package net.etfbl.facultyapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.facultyapp.service.ApiClient;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String json = """
				{
					"username": "%s",
					"password": "%s",
					"role": "FACULTY"
				}
				""".formatted(username, password);
		
		String responseLogin = ApiClient.post("/auth/login", json);
		
		if(responseLogin != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", responseLogin);
			
			response.sendRedirect(request.getContextPath() + "/dashboard");
		}else {
			request.setAttribute("error", "Invalid username or password");
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}

}
