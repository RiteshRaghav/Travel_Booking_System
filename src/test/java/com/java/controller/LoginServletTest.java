package com.java.controller;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.dao.TravelDAO;
import com.java.model.user;

@ExtendWith(MockitoExtension.class)
public class LoginServletTest {

    @InjectMocks
    private loginservlet servlet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;
    
    @Mock
    private HttpSession session;
    
    @Mock
    private TravelDAO travelDAO;
    
    @BeforeEach
    public void setUp() {
        when(request.getSession()).thenReturn(session);
    }
    
    @Test
    public void testAdminLogin() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("satyamjha@gmail.com");
        when(request.getParameter("password")).thenReturn("1234@");
        when(travelDAO.checkLogin("satyamjha@gmail.com", "1234@")).thenReturn(new user());
        when(travelDAO.getUserRole("satyamjha@gmail.com", "1234@")).thenReturn("admin");
        
        servlet.doPost(request, response);
        
        verify(session).setAttribute("user", any(user.class));
        verify(session).setAttribute("role", "admin");
        verify(response).sendRedirect("admin_panel.jsp");
    }
    
    @Test
    public void testUserLogin() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("userpass");
        when(travelDAO.checkLogin("user@example.com", "userpass")).thenReturn(new user());
        when(travelDAO.getUserRole("user@example.com", "userpass")).thenReturn("user");
        
        servlet.doPost(request, response);
        
        verify(session).setAttribute("user", any(user.class));
        verify(session).setAttribute("role", "user");
        verify(response).sendRedirect("user_dashboard.jsp");
    }
    
    @Test
    public void testInvalidLogin() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("invalid@example.com");
        when(request.getParameter("password")).thenReturn("invalidpass");
        when(travelDAO.checkLogin("invalid@example.com", "invalidpass")).thenReturn(null);
        
        servlet.doPost(request, response);
        
        verify(response).sendRedirect("login.jsp?error=Invalid credentials");
    }

    @Test
    public void testMissingUsername() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("password");

        servlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp?error=Invalid credentials");
    }

    @Test
    public void testMissingPassword() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("");

        servlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp?error=Invalid credentials");
    }

    @Test
    public void testInvalidRole() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("userpass");
        when(travelDAO.checkLogin("user@example.com", "userpass")).thenReturn(new user());
        when(travelDAO.getUserRole("user@example.com", "userpass")).thenReturn("unknown");

        servlet.doPost(request, response);

        verify(response).sendRedirect("login.jsp?error=Invalid role");
    }

    @Test
    public void testSessionHandling() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("user@example.com");
        when(request.getParameter("password")).thenReturn("userpass");
        when(travelDAO.checkLogin("user@example.com", "userpass")).thenReturn(new user());
        when(travelDAO.getUserRole("user@example.com", "userpass")).thenReturn("user");

        servlet.doPost(request, response);

        verify(session).setAttribute("user", any(user.class));
        verify(session).setAttribute("role", "user");
        verify(response).sendRedirect("user_dashboard.jsp");
    }
}
