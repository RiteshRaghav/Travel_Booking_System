<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cab Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            margin: 20px auto;
            max-width: 1200px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .cab-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 300px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }
        .cab-card img {
            width: 100px; /* Smaller width */
            height: 75px; /* Smaller height */
            object-fit: contain;
            margin: auto; /* Center the image */
        }
        .cab-card-content {
            padding: 15px;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }
        .cab-card h2 {
            font-size: 18px;
            margin: 0 0 10px;
        }
        .cab-card p {
            margin: 5px 0;
            color: #555;
        }
        .cab-card-footer {
            padding: 10px;
            background: #007bff; /* Blue background */
            color: white;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.3s;
        }
        .cab-card-footer:hover {
            background: #0056b3; /* Darker blue on hover */
        }
    </style>
    <script>
        function displayCabs(cabData) {
            const container = document.getElementById('cab-container');
            container.innerHTML = ''; // Clear existing content

            // Display groups data
            Object.keys(cabData.groups).forEach(groupKey => {
                const group = cabData.groups[groupKey];

                const cabGroupCard = document.createElement('div');
                cabGroupCard.className = 'cab-card';

                const groupImage = document.createElement('img');
                groupImage.src = group.img || 'default-image.jpg';
                groupImage.alt = group.car_name || 'Car Image';
                cabGroupCard.appendChild(groupImage);

                const groupContent = document.createElement('div');
                groupContent.className = 'cab-card-content';

                const groupCarName = document.createElement('h2');
                groupCarName.textContent = group.car_name || 'N/A';
                groupContent.appendChild(groupCarName);

                const groupPickupType = document.createElement('p');
                groupPickupType.textContent = `Pickup Type: ${group.pickupType || 'N/A'}`;
                groupContent.appendChild(groupPickupType);

                const groupRating = document.createElement('p');
                groupRating.textContent = `Rating: ${group.rating != null ? group.rating : 'N/A'}`;
                groupContent.appendChild(groupRating);

                const groupSeats = document.createElement('p');
                groupSeats.textContent = `Seats: ${group.max_seats != null ? group.max_seats : 'N/A'}`;
                groupContent.appendChild(groupSeats);

                const groupPrice = document.createElement('p');
                groupPrice.textContent = `Price: ${group.min_price != null ? group.min_price : 'N/A'}`;
                groupContent.appendChild(groupPrice);

                cabGroupCard.appendChild(groupContent);

                const groupFooter = document.createElement('div');
                groupFooter.className = 'cab-card-footer';
                groupFooter.textContent = 'Book Now';
                groupFooter.onclick = function() {
                    window.location.href = group.dplnk;
                };
                cabGroupCard.appendChild(groupFooter);

                container.appendChild(cabGroupCard);
            });
        }

        function parseCabResults() {
            const cabResultsElement = document.getElementById('Cabresults');
            if (cabResultsElement) {
                try {
                    const cabResults = JSON.parse(cabResultsElement.textContent);
                    if (cabResults.groups) {
                        displayCabs(cabResults);
                    } else {
                        alert('No groups found in the response.');
                    }
                } catch (error) {
                    console.error('Error parsing JSON:', error);
                    alert('Error parsing cab results');
                }
            }
        }

        document.addEventListener('DOMContentLoaded', parseCabResults);
    </script>
</head>
<body>
    <div class="container" id="cab-container"></div>
    <pre id="Cabresults" style="display: none;"><%= request.getAttribute("carSearchResults") %></pre>
</body>
</html>
