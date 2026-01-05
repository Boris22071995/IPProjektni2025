package net.etfbl.facultyapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorkLogServlet
 */
@WebServlet("/worklogs")
public class WorkLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkLogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 List<Map<String, String>> students = List.of(
		            Map.of("id", "1", "name", "Petar Petrovic", "index", "IB2001"),
		            Map.of("id", "2", "name", "Marko Markovic", "index", "IB2002")
		        );

		        // koji student je izabran
		        String studentId = request.getParameter("studentId");

		        // MOCK work log
		        List<Map<String, String>> workLogs = List.of(
		            Map.of("week", "1", "activities", "Setup projekta, upoznavanje tima"),
		            Map.of("week", "2", "activities", "Rad na REST API-ju")
		        );

		        // MOCK feedback
		        Map<String, String> companyFeedback = Map.of(
		            "comment", "Student je pokazao visok nivo odgovornosti.",
		            "createdAt", "2025-01-10"
		        );

		        // MOCK ocjena
		        Map<String, String> facultyGrade = Map.of(
		            "grade", "9",
		            "comment", "Vrlo dobar napredak i komunikacija."
		        );

		        request.setAttribute("students", students);
		        request.setAttribute("selectedStudentId", studentId);

		        if (studentId != null) {
		            request.setAttribute("workLogs", workLogs);
		            request.setAttribute("companyFeedback", companyFeedback);
		            request.setAttribute("facultyGrade", facultyGrade);
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
