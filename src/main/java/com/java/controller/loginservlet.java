package com.java.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.java.dao.TravelDAO;
import com.java.model.user;

@WebServlet(name = "loginservlet", urlPatterns = { "/loginservlet" })
public class loginservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private TravelDAO travelDAO;

    public void init() {
        travelDAO = new TravelDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Your logic here
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("username");
        String password = request.getParameter("password");
        user user = travelDAO.checkLogin(email, password);
        
        if (user != null) {
            request.getSession().setAttribute("user", user);
            String role = travelDAO.getUserRole(email, password);

            if ("admin".equals(role)) {
                response.sendRedirect("admin_panel.jsp");
            } else if ("user".equals(role)) {
                response.sendRedirect("user_dashboard.jsp");
            } else {
                response.sendRedirect("login.jsp?error=Invalid role");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
