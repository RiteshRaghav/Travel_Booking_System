<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Flight Booking</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="Style_flight.css">
</head>
<body>
    <header>
        <h1>Book Your Flight</h1>
    </header>
    <main>
        <form id="flight-booking-form" method="post" action="FlightSearchServlet">
            <div class="form-group">
                <label for="departure-airport">Departure Airport Code:</label>
                <input type="text" id="departure-airport" name="departure-airport" required>
            </div>
            <div class="form-group">
                <label for="destination-airport">Destination Airport Code:</label>
                <input type="text" id="destination-airport" name="destination-airport" required>
            </div>
            <div class="form-group">
                <label for="departure-date">Departure Date:</label>
                <input type="date" id="departure-date" name="departure-date" required>
            </div>
            <div class="form-group">
                <label for="return-date">Return Date:</label>
                <input type="date" id="return-date" name="return-date">
            </div>
            <div class="form-group">
                <label for="adults">Adults:</label>
                <input type="number" id="adults" name="adults" min="1" required>
            </div>
            <div class="form-group">
                <label for="children">Children:</label>
                <input type="number" id="children" name="children" min="0">
            </div>
            <div class="form-group">
                <label for="infants">Infants:</label>
                <input type="number" id="infants" name="infants" min="0">
            </div>
            <div class="form-group">
                <label for="cabin-class">Cabin Class:</label>
                <select id="cabin-class" name="cabin-class">
                    <option value="economy">Economy</option>
                    <option value="business">Business</option>
                    <option value="first">First Class</option>
                    
                </select>
            </div>
            <button type="submit">Search Flights</button>
        </form>

        <!-- Results Section -->
        <div id="results" style="display:none;">
            <h2>Flight Search Results</h2>
            <pre id="results-output"></pre>
        </div>
    </main>
    <script src="app.js"></script>
</body>
</html>
