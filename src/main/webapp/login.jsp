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
    <h2>Login Form</h2>
    <form id="loginForm">
        <label for="email">Username:</label>
        <input type="text" id="email" name="email" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <button type="button" onclick="login()">Login</button>
    </form>

    <a  class="signup" href="signup.jsp">Register</a>
    <a  class="forget" href="forgotPass.jsp">Forget Password</a>
</div>

    <script>
        function login() {
            const username = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            // Simulating a successful login (replace with an actual API call)
            if (username === "ayush12@gmail.com" && password === "1234") {
                
                window.location.href = "index.jsp"; // Redirect to index page
            } 
            else if (username==="" || password===""){
                 alert("please fill the username and password ! ");
            }
            else {
                alert("Invalid Credentials");
            }
        }
    </script>
    
        
    </div>

    
    
</body>
</html>
