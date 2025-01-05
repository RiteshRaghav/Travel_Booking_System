package com.java.servlet;

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
        String Email = request.getParameter("username");
        String password = request.getParameter("password");
        user user = travelDAO.checkLogin(Email, password);
        
        if (user != null) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
