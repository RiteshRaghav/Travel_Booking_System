package com.java.dao;

import java.sql.*;
import java.util.*;
import com.java.model.user;

public class TravelDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/Userdb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "Shubham@123#";
    
    private static final String INSERT_USER_SQL = "INSERT INTO users (UserName, Email, paswd, country) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE Id=?";
    private static final String SELECT_ALL_USER = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE Id=?";
    private static final String UPDATE_USERS_SQL = "UPDATE users SET UserName=?, Email=?, paswd=?, country=? WHERE Id=?";
    private static final String GET_USER_ROLE_SQL = "SELECT role FROM users WHERE Email = ? AND paswd = ?";

    public TravelDAO() {
    }

    // Database connection
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(user user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPaswd());
            preparedStatement.setString(4, user.getCountry());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public user selectUser(int Id) {
        user user = new user();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString("UserName"));
                user.setEmail(resultSet.getString("Email"));
                user.setPaswd(resultSet.getString("paswd"));
                user.setCountry(resultSet.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<user> selectAllUsers() {
        List<user> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String userName = resultSet.getString("UserName");
                String email = resultSet.getString("Email");
                String paswd = resultSet.getString("paswd");
                String country = resultSet.getString("country");
                users.add(new user(userName, email, paswd, country));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int Id) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, Id);
            status = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public boolean updateUser(user user) {
        boolean status = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPaswd());
            preparedStatement.setString(4, user.getCountry());
            status = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    public user checkLogin(String email, String password) {
        user user = null;
        String sql = "SELECT * FROM users WHERE Email = ? AND paswd = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new user();
                user.setUserName(resultSet.getString("UserName"));
                user.setEmail(resultSet.getString("Email"));
                user.setPaswd(resultSet.getString("paswd"));
                user.setCountry(resultSet.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getUserRole(String email, String password) {
        if ("satyamjha@gmail.com".equals(email) && "1234@".equals(password)) {
            return "admin";
        }
        String role = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_ROLE_SQL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
