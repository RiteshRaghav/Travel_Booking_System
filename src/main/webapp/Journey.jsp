<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Portal</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            transition: background-image 0.5s ease-in-out;
        }

        .container {
            margin: 0 auto;
            width: 90%;
            max-width: 900px;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 20px;
            border-radius: 10px;
            color: white;
        }

        .tabs {
            display: flex;
            justify-content: space-around;
            margin-bottom: 20px;
        }

        .tabs button {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .tabs button.active {
            background-color: #0056b3;
        }

        .tabs button:hover {
            background-color: #0056b3;
        }

        .form-section {
            display: none;
        }

        .form-section.active {
            display: block;
        }

        .form-box {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .form-box input, .form-box select, .form-box button {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .form-box button {
            background-color: #007bff;
            color: white;
            cursor: pointer;
        }

        .form-box button:hover {
            background-color: #0056b3;
        }

        .options label {
            display: flex;
            align-items: center;
        }

        .options input {
            margin-right: 5px;
        }
    </style>
    <script>
        function switchTab(tabName) {
            // Change active tab
            document.querySelectorAll('.tabs button').forEach(button => button.classList.remove('active'));
            document.getElementById(tabName + '-tab').classList.add('active');

            // Show the relevant form section
            document.querySelectorAll('.form-section').forEach(section => section.classList.remove('active'));
            document.getElementById(tabName + '-section').classList.add('active');

            // Change background image
            let backgroundImage;
            switch (tabName) {
                case 'flights':
                    backgroundImage = "url('img/passenger-airplanes.jpg')";
                    break;
                case 'hotels':
                    backgroundImage = "url('img/hotel-background.jpg')";
                    break;
                case 'cabs':
                    backgroundImage = "url('img/cab-background.jpg')";
                    break;
            }
            document.body.style.backgroundImage = backgroundImage;
        }

        // Default tab on load
        window.onload = () => switchTab('flights');
    </script>
</head>
<body>
    <div class="container">
        <div class="tabs">
            <button id="flights-tab" onclick="switchTab('flights')">Flights</button>
            <button id="hotels-tab" onclick="switchTab('hotels')">Hotels</button>
            <button id="cabs-tab" onclick="switchTab('cabs')">Cab Hire</button>
        </div>

        <!-- Flights Section -->
        <div id="flights-section" class="form-section">
            <h2>Flight Search</h2>
            <form action="JourneyServlet" method="post" class="form-box">
                <input type="text" name="departureCity" placeholder="From" required>
                <input type="text" name="destinationCity" placeholder="To" required>
                <input type="month" name="departureDate" required>
                <div class="options">
                    <label><input type="checkbox" name="nearbyAirports"> Add nearby airports</label>
                    <label><input type="checkbox" name="directFlights"> Direct flights</label>
                </div>
                <button type="submit">Search Flights</button>
            </form>
        </div>

        <!-- Hotels Section -->
        <div id="hotels-section" class="form-section">
            <h2>Hotel Search</h2>
            <form action="HotelServlet" method="post" class="form-box">
                <input type="text" name="hotelCity" placeholder="City" required>
                <input type="date" name="checkInDate" placeholder="Check-in Date" required>
                <input type="date" name="checkOutDate" placeholder="Check-out Date" required>
                <button type="submit">Search Hotels</button>
            </form>
        </div>

        <!-- Cab Hire Section -->
   <div id="cabs-section" class="form-section">
    <h2>Cab Hire</h2>
    <form action="CabServlet" method="post" class="form-box">
        <input type="text" name="pickUpLocation" placeholder="Pick-up Location" required>
        <input type="text" name="dropOffLocation" placeholder="Drop-off Location" required>

        <label for="pickUpDate">Pick-up Date:</label>
        <input type="date" id="pickUpDate" name="pickUpDate" required>

        <label for="pickUpTime">Pick-up Time:</label>
        <input type="time" id="pickUpTime" name="pickUpTime" required>

        <label for="dropOffDate">Drop-off Date:</label>
        <input type="date" id="dropOffDate" name="dropOffDate" required>

        <label for="dropOffTime">Drop-off Time:</label>
        <input type="time" id="dropOffTime" name="dropOffTime" required>

        <button type="submit">Search Cabs</button>
    </form>
</div>


    </div>
</body>
</html>
