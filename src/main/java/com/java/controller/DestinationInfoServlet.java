package com.java.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DestinationInfoServlet
 */
@WebServlet("/Journey")
public class DestinationInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Logger for debugging
    private static final Logger logger = Logger.getLogger(DestinationInfoServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationInfoServlet() {
        super();
    }

    /**
     * Handles GET requests.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles POST requests.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes both GET and POST requests.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Log request received
            logger.info("Processing request to DestinationInfo endpoint.");

            // Forward to a default JSP page (generic information page)
            request.getRequestDispatcher("Journey.jsp").forward(request, response);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred while processing the request.", e);
            forwardToErrorPage(request, response, "An unexpected error occurred. Please try again later.");
        }
    }

    /**
     * Forwards the request to the error page with a custom error message.
     * 
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param errorMessage The custom error message to display on the error page.
     * @throws ServletException
     * @throws IOException
     */
    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}
