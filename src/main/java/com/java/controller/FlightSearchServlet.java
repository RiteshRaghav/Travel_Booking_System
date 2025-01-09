package com.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FlightSearchServlet")
public class FlightSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromEntityId = request.getParameter("departure-airport");
        String toEntityId = request.getParameter("destination-airport");
        String departDate = request.getParameter("departure-date");
        String returnDate = request.getParameter("return-date");
        String adults = request.getParameter("adults");
        String children = request.getParameter("children");
        String infants = request.getParameter("infants");
        String cabinClass = request.getParameter("cabin-class");

        // Debugging: Log the received parameters (optional)
        System.out.println("Flight Search Parameters:");
        System.out.println("From: " + fromEntityId);
        System.out.println("To: " + toEntityId);
        System.out.println("Departure Date: " + departDate);
        System.out.println("Return Date: " + returnDate);
        System.out.println("Adults: " + adults);
        System.out.println("Children: " + children);
        System.out.println("Infants: " + infants);
        System.out.println("Cabin Class: " + cabinClass);

        // Example API call can be done here (replace this with your actual API call)
        // You might use HttpClient or another method to make the API request
        // Note: Don't forget to handle exceptions and errors properly

        // Forward to a results page
        request.setAttribute("fromEntityId", fromEntityId);
        request.setAttribute("toEntityId", toEntityId);
        request.setAttribute("departDate", departDate);
        request.setAttribute("returnDate", returnDate);
        request.setAttribute("adults", adults);
        request.setAttribute("children", children);
        request.setAttribute("infants", infants);
        request.setAttribute("cabinClass", cabinClass);

        request.getRequestDispatcher("results.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle GET requests here if needed
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
}
