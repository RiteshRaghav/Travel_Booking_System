package com.java.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.model.user;

@ExtendWith(MockitoExtension.class)
public class TravelDAOTest {

    @Mock
    private Connection connection;
    
    @Mock
    private PreparedStatement preparedStatement;
    
    @Mock
    private ResultSet resultSet;
    
    @InjectMocks
    private TravelDAO travelDAO;
    
    private user testUser;

    @BeforeEach
    public void setUp() throws SQLException {
        testUser = new user("John Doe", "john@example.com", "password", "USA");
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }
    
    @Test
    public void testInsertUser() throws SQLException {
        doNothing().when(preparedStatement).executeUpdate();

        travelDAO.insertUser(testUser);
        
        verify(preparedStatement).setString(1, testUser.getUserName());
        verify(preparedStatement).setString(2, testUser.getEmail());
        verify(preparedStatement).setString(3, testUser.getPaswd());
        verify(preparedStatement).setString(4, testUser.getCountry());
        verify(preparedStatement).executeUpdate();
    }
    
    @Test
    public void testSelectUser() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("UserName")).thenReturn(testUser.getUserName());
        when(resultSet.getString("Email")).thenReturn(testUser.getEmail());
        when(resultSet.getString("paswd")).thenReturn(testUser.getPaswd());
        when(resultSet.getString("country")).thenReturn(testUser.getCountry());
        
        user user = travelDAO.selectUser(1);
        
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getPaswd(), user.getPaswd());
        assertEquals(testUser.getCountry(), user.getCountry());
    }
    
    @Test
    public void testSelectAllUsers() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("UserName")).thenReturn(testUser.getUserName());
        when(resultSet.getString("Email")).thenReturn(testUser.getEmail());
        when(resultSet.getString("paswd")).thenReturn(testUser.getPaswd());
        when(resultSet.getString("country")).thenReturn(testUser.getCountry());
        
        List<user> users = travelDAO.selectAllUsers();
        
        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        user user = users.get(0);
        assertEquals(testUser.getUserName(), user.getUserName());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getPaswd(), user.getPaswd());
        assertEquals(testUser.getCountry(), user.getCountry());
    }
    
    @Test
    public void testDeleteUser() throws SQLException {
        when(preparedStatement.execute()).thenReturn(true);

        boolean status = travelDAO.deleteUser(1);
        
        assertTrue(status);
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).execute();
    }
    
    @Test
    public void testUpdateUser() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean status = travelDAO.updateUser(testUser);
        
        assertTrue(status);
        verify(preparedStatement).setString(1, testUser.getUserName());
        verify(preparedStatement).setString(2, testUser.getEmail());
        verify(preparedStatement).setString(3, testUser.getPaswd());
        verify(preparedStatement).setString(4, testUser.getCountry());
        verify(preparedStatement).executeUpdate();
    }
    
    @Test
    public void testCheckLogin() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("UserName")).thenReturn(testUser.getUserName());
        when(resultSet.getString("Email")).thenReturn(testUser.getEmail());
        when(resultSet.getString("paswd")).thenReturn(testUser.getPaswd());
        when(resultSet.getString("country")).thenReturn(testUser.getCountry());
        
        user user = travelDAO.checkLogin("john@example.com", "password");
        
        assertNotNull(user);
        assertEquals(testUser.getUserName(), user.getUserName());
        assertEquals(testUser.getEmail(), user.getEmail());
        assertEquals(testUser.getPaswd(), user.getPaswd());
        assertEquals(testUser.getCountry(), user.getCountry());
    }
    
    @Test
    public void testGetUserRole() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("role")).thenReturn("user");
        
        String role = travelDAO.getUserRole("john@example.com", "password");
        
        assertNotNull(role);
        assertEquals("user", role);
    }
}
