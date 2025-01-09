<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.java.model.FlightQuote" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.lang.SuppressWarnings" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            text-align: center;
        }
        .cards-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            margin-top: 20px;
        }
        .card {
            background-color: #f2f2f2;
            border: 1px solid #ddd;
            border-radius: 10px;
            padding: 20px;
            width: 300px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }
        .card h2 {
            margin-top: 0;
        }
        .card p {
            margin: 5px 0;
        }
        .card button {
            background-color: #007bff;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .card button:hover {
            background-color: #0056b3;
        }
        .card:hover {
            transform: scale(1.05);
        }
        .error-message {
            color: red;
            text-align: center;
            font-size: 1.2em;
        }
        .loading {
            display: none;
            text-align: center;
            font-size: 1.5em;
            color: #007bff;
        }
    </style>
</head>
<body>
    <h1>Flight Search Results</h1>

    <!-- Display error message if available -->
    <% if (request.getAttribute("errorMessage") != null) { %>
        <div class="error-message">
            <p><%= request.getAttribute("errorMessage") %></p>
        </div>
    <% } %>

    <!-- Loading indicator -->
    <div class="loading" id="loadingMessage">Loading results, please wait...</div>

    <!-- Display flight quotes if available -->
    <% 
        @SuppressWarnings("unchecked")
        List<FlightQuote> flightQuotes = (List<FlightQuote>) request.getAttribute("flightQuotes");
        if (flightQuotes != null && !flightQuotes.isEmpty()) { 
    %>
        <div class="cards-container" id="flightResultsCards">
            <% 
                for (int i = 0; i < flightQuotes.size(); i++) {
                    FlightQuote quote = flightQuotes.get(i);
                    String flightCode = "ingo6E" + (flightQuotes.size() + 1);
            %>
                <div class="card">
                    <h2><%= flightCode %></h2>
                    <p><strong>Date:</strong> <%= quote.getDate() %></p>
                    <p><strong>From:</strong> <%= quote.getFrom() %></p>
                    <p><strong>To:</strong> <%= quote.getTo() %></p>
                    <p><strong>Price IN $:</strong> <%= quote.getPrice() %></p>
                    <button onclick="bookFlight()">Book Now</button>
                </div>
            <% 
                }
            %>
        </div>
    <% } %>

    <script>
        function bookFlight() {
            alert("Flight booking is not implemented yet.");
        }
    </script>

</body>
</html>
