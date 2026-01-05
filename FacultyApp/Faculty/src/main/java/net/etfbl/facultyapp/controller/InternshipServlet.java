package net.etfbl.facultyapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.etfbl.facultyapp.dto.InternshipDTO;
import net.etfbl.facultyapp.service.ApiClient;

/**
 * Servlet implementation class InternshipServlet
 */
@WebServlet("/internships")
public class InternshipServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InternshipServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String json = ApiClient.get("/internships");

        if (json != null) {
            ObjectMapper mapper = new ObjectMapper();
            List<InternshipDTO> internships =
                mapper.readValue(json, new TypeReference<List<InternshipDTO>>() {});

            request.setAttribute("internships", internships);
        }

//        request.getRequestDispatcher("/WEB-INF/jsp/internships.jsp")
//               .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
