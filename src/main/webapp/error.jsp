<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            color: #333;
            text-align: center;
        }
        .error-container {
            margin: 50px auto;
            width: 80%;
            max-width: 600px;
            padding: 20px;
            background-color: #ffcccc;
            border-radius: 10px;
            border: 1px solid red;
        }
        h1 {
            color: red;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error: Invalid Destination</h1>
        <p>${errorMessage}</p>  <!-- Displaying the error message -->
        <a href="/TravelBooking/">Go back to the homepage</a>
    </div>
</body>
</html>
