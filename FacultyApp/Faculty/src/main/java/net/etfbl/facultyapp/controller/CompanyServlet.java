package net.etfbl.facultyapp.controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.etfbl.facultyapp.dto.CompanyDTO;
import net.etfbl.facultyapp.service.ApiClient;

/**
 * Servlet implementation class CompanyServlet
 */
@WebServlet("/company")
public class CompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String json = ApiClient.get("/companies");
		    if (json != null) {
		        ObjectMapper mapper = new ObjectMapper();
		        List<CompanyDTO> companies =
		                mapper.readValue(json, new TypeReference<List<CompanyDTO>>() {});
		        

		        request.setAttribute("companies", companies);
		    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String action = request.getParameter("action");

	    if (action != null) {

	        String id = request.getParameter("id");

	        if ("activate".equals(action)) {
	            ApiClient.put("/companies/" + id + "/activate");
	        }
	        else if ("deactivate".equals(action)) {
	            ApiClient.put("/companies/" + id + "/deactivate");
	        }

	        response.sendRedirect(
	            request.getContextPath() + "/dashboard?page=companies"
	        );
	        return;
	    }

	    String companyName = request.getParameter("companyName");
	    String companyDescription = request.getParameter("companyDescription");
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    String json = """
	        {
	          "companyName": "%s",
	          "companyDescription": "%s",
	          "username": "%s",
	          "password": "%s"
	        }
	        """.formatted(
	            escape(companyName),
	            escape(companyDescription),
	            escape(username),
	            escape(password)
	        );

	    String success = ApiClient.post("/companies", json);

	    if (success != null) {
	        response.sendRedirect(
	            request.getContextPath() + "/dashboard?page=companies"
	        );
	    } else {
	        request.setAttribute("error", "Error creating company");
	        request.getRequestDispatcher("/WEB-INF/jsp/companies.jsp")
	               .forward(request, response);
	    }
	}

	
	private String escape(String value) {
	    return value == null ? "" : value.replace("\"", "\\\"");
	}



}
