package com.java.dao;
import java.sql.*;
import java.util.*;

import com.java.model.user;

public class TravelDAO {
	private String jdbcURL="jdbc:mysql://localhost:3306/UserDb";
	private String jdbcUserName="root";
	private String jdbcPassword="1234";
	
	private static final String INSERT_USER_SQL="INSERT INTO users"+"(UserName,Email,paswd,country) VALUES "+" (?,?,?,?)";
	private static final String SELECT_USER_BY_NAME="SELECT * FROM users where UserName=?";
	private static final String SELECT_ALL_USER="SELECT * FROM users";
	private static final String DELETE_USER_SQL="delete from users where UserName=?";
	private static final String UPDATE_USERS_SQL="update users set UserName=?,Email=?,paswd=?,country=?  where UserName=?";
	
	
	public TravelDAO() {
		
	}
	//database connection
	public Connection getConnection()
	{
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(jdbcURL,jdbcUserName,jdbcPassword);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return connection;
		
		
	}
	public void insertUser(user user) {
		TravelDAO dao=new TravelDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement(INSERT_USER_SQL);
			preparedStatement.setString(1,user.getUserName());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getPaswd());
			preparedStatement.setString(4,user.getCountry());
			
			preparedStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public user selectUser(String UserName) {
		user user=new user();
		
		TravelDAO dao=new TravelDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_USER_BY_NAME);
			preparedStatement.setString(1, UserName);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			user.setUserName(resultSet.getString("UserName"));
			user.setEmail(resultSet.getString("Email"));
			user.setPaswd(resultSet.getString("paswd"));
			user.setCountry(resultSet.getString("country"));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return user;
		
	}
	public List<user> selectAllUsers(){
		List<user> users=new ArrayList<user>();
		TravelDAO dao=new TravelDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement(SELECT_ALL_USER);
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				String UserName=resultSet.getString("UserName");
				String Email=resultSet.getString("Email");
				String paswd=resultSet.getString("paswd");
				String country=resultSet.getString("country");
				
				
				users.add(new user(UserName,Email,paswd,country));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return users;
		
	}
	public boolean deleteUser(String UserName) {
		boolean status =false ;
		TravelDAO dao=new TravelDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement(DELETE_USER_SQL);
			preparedStatement.setString(1,UserName);
			
			status=preparedStatement.execute();
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return status;
		
	}
	
	public boolean updateUser(user user) {
		boolean status =false ;
		TravelDAO dao=new TravelDAO();
		try(Connection connection=dao.getConnection()){
			PreparedStatement preparedStatement=connection.prepareStatement( UPDATE_USERS_SQL);
			preparedStatement.setString(1,user.getUserName());
			preparedStatement.setString(2,user.getEmail());
			preparedStatement.setString(3,user.getPaswd());
			preparedStatement.setString(4,user.getCountry());
			
			preparedStatement.executeUpdate();
			
			
			status=preparedStatement.executeUpdate()>0;
			  
			
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		return status;
		
		
	}
	
	public static void main(String args[]) {
		TravelDAO dao=new TravelDAO();
		
		if(dao.getConnection()!=null) {
			System.out.println("Successfully connected to the database");
		}else {
			System.out.println("problem in database connection");
		}
		user user=new user("raghav","raghav@abc","1234","India");
		//dao.insertUser(user);
		user user1=dao.selectUser("raghav");
		System.out.println(user1);
	}
	

	
	

}
