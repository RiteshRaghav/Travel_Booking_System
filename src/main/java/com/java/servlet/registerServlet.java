package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.dao.TravelDAO;
import com.java.model.user;

@WebServlet(name = "registerServlet", urlPatterns = { "/registerServlet" })
public class registerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TravelDAO travelDAO;     

    public void init() {
        travelDAO = new TravelDAO();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your logic here
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("nationality");

        user newUser = new user(userName, email, password, country);
        travelDAO.insertUser(newUser);

        response.sendRedirect("login.jsp");
    }
}
