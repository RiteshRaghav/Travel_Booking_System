<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style_login.css">
</head>
<body>
    <div class="container">
        <h2>Login Form</h2>
        <form id="loginForm" action="loginservlet" method="post">
            <label for="email">Username:</label>
            <input type="text" id="email" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <button type="submit">Login</button>
        </form>

        <a class="signup" href="signup.jsp">Register</a>
        <a class="forget" href="forgotPass.jsp">Forgot Password</a>
    </div>
</body>
</html>
