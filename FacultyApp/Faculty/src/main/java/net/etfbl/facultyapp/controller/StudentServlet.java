package net.etfbl.facultyapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.etfbl.facultyapp.dto.StudentDTO;
import net.etfbl.facultyapp.service.ApiClient;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;


/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/students")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  
	    maxFileSize = 1024 * 1024 * 5,     
	    maxRequestSize = 1024 * 1024 * 10  
	)
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = ApiClient.get("/students/faculty");

		if (json != null) {
			ObjectMapper mapper = new ObjectMapper();

			List<StudentDTO> students = mapper.readValue(json, new TypeReference<List<StudentDTO>>() {
			});

			request.setAttribute("students", students);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		// ako nema action → CREATE (postojeći Add Student)
		if (action == null || action.isEmpty()) {
			handleCreate(request, response);
			return;
		}

		switch (action) {
		case "edit":
			handleEdit(request);
			response.sendRedirect(request.getContextPath() + "/dashboard?page=students");
			break;

		case "delete":
			handleDelete(request);
			response.sendRedirect(request.getContextPath() + "/dashboard?page=students");
			break;
		
		case "csv":
		    handleCsvUpload(request);
		    response.sendRedirect(request.getContextPath() + "/dashboard?page=students");
		    break;
	

		default:
			response.sendRedirect(request.getContextPath() + "/dashboard?page=students");
		}


	}

	private void handleCsvUpload(HttpServletRequest request) {

	    try {
	        Part filePart = request.getPart("csvFile");

	        try (BufferedReader br = new BufferedReader(
	                new InputStreamReader(filePart.getInputStream(),"UTF-8"))) {

	            String line;
	            boolean first = true;

	            while ((line = br.readLine()) != null) {

	                // preskoči header
	                if (first) {
	                    first = false;
	                    continue;
	                }

	                String[] cols = line.split(",");

	                if (cols.length < 6)
	                    continue;

	                String json = """
	                    {
	                      "firstName": "%s",
	                      "lastName": "%s",
	                      "indexNumber": "%s",
	                      "yearOfStudy": %s,
	                      "username": "%s",
	                      "password": "%s"
	                    }
	                    """.formatted(
	                        escape(cols[0].trim()),
	                        escape(cols[1].trim()),
	                        escape(cols[2].trim()),
	                        cols[3].trim(),
	                        escape(cols[4].trim()),
	                        escape(cols[5].trim())
	                    );

	                ApiClient.post("/students", json);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private String escape(String v) {
		return v == null ? "" : v.replace("\"", "\\\"");
	}

	private void handleCreate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String json = """
				{
				"firstName": "%s",
				"lastName": "%s",
				"indexNumber": "%s",
				"yearOfStudy": %s,
				"username": "%s",
				"password": "%s"
				}
				""".formatted(escape(request.getParameter("firstName")), escape(request.getParameter("lastName")),
				escape(request.getParameter("indexNumber")), request.getParameter("yearOfStudy"),
				escape(request.getParameter("username")), escape(request.getParameter("password")));

		String result = ApiClient.post("/students", json);

		if (result != null) {
			response.sendRedirect(request.getContextPath() + "/dashboard?page=students");
		} else {
			request.setAttribute("error", "Error creating student");
			request.getRequestDispatcher("/WEB-INF/jsp/students.jsp").forward(request, response);
		}
	}

	private void handleEdit(HttpServletRequest request) {

	    String password = request.getParameter("password");

	    String json;

	    if (password == null || password.isBlank()) {
	        json = """
	            {
	              "firstName": "%s",
	              "lastName": "%s",
	              "yearOfStudy": %s,
	              "username": "%s"
	              
	            }
	            """.formatted(
	                escape(request.getParameter("firstName")),
	                escape(request.getParameter("lastName")),
	                request.getParameter("yearOfStudy"),
	                escape(request.getParameter("username"))
	            );
	    } else {
	        json = """
	            {
	              "firstName": "%s",
	              "lastName": "%s",
	              "yearOfStudy": %s,
	              "username": "%s",
	              "password": "%s"
	            }
	            """.formatted(
	                escape(request.getParameter("firstName")),
	                escape(request.getParameter("lastName")),
	                request.getParameter("yearOfStudy"),
	                escape(request.getParameter("username")),
	                escape(password)
	            );
	    }

	    String index = request.getParameter("indexNumber");
	    index = index.replace("/", "-");
	    ApiClient.put("/students/" + index, json);
	}


	private void handleDelete(HttpServletRequest request) {
		String index = request.getParameter("indexNumber");
		index = index.replace("/", "-");
		ApiClient.delete("/students/" + index);
	}

}
