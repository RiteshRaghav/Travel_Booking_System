<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <link rel="stylesheet" href="forgetPass.css">
</head>
<body>

<div class="forgot-password-container">
    <h2>Forgot Your Password?</h2>
    <p>Enter your email address and we'll send you a link to reset your password.</p>
    
    <form action="/submit-forgot-password" method="POST">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email address" required>
        
        <input type="submit" value="Submit">
    </form>
</div>

</body>
</html>
