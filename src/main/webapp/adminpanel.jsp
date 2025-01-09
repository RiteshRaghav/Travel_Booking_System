<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <link rel="stylesheet" href="style_admin.css">
</head>
<body>
    <div class="sidebar">
        <h2>Admin Panel</h2>
        <ul>
            <li><a href="#dashboard">Dashboard</a></li>
            <li><a href="#users">Manage Users</a></li>
            <li><a href="#reports">Reports</a></li>
            <li><a href="#settings">Settings</a></li>
            <li><a href="logout.jsp">Logout</a></li>
        </ul>
    </div>
    <div class="main-content">
        <div id="dashboard" class="section">
            <h2>Dashboard</h2>
            <p>Overview of the system's performance and key metrics.</p>
        </div>
        <div id="users" class="section">
            <h2>Manage Users</h2>
            <p>List of registered users with options to add, edit, or delete users.</p>
            <button>Add New User</button>
            <!-- User management table or form can go here -->
        </div>
        <div id="reports" class="section">
            <h2>Reports</h2>
            <p>Generate and view system reports.</p>
            <!-- Reports generation and viewing tools can go here -->
        </div>
        <div id="settings" class="section">
            <h2>Settings</h2>
            <p>Update system preferences and configurations.</p>
            <!-- Settings form can go here -->
        </div>
    </div>
</body>
</html>
