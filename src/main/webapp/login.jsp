<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="style_login.css"> <!-- Optional: Same styling -->
</head>
<body>
    <div class="container">
        <h1> Login</h1>
        
        <form action="#" method="POST">
            
            

            <!-- Email -->
            <div class="form-group">
                <label for="email">Email Address:</label>
                <input type="email" id="email" name="email" placeholder="Enter your email" required>
            </div>

            <!-- Password -->
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Create a password" required>
            </div>
            <button type="submit">
                <a href="index.jsp">Login</a>
            </button>
        </form>
    
        <a  class="signup" href="signup.jsp">Register</a>
        <a  class="forget" href="forgotPass.jsp">Forget Password</a>
    </div>
    
</body>
</html>
