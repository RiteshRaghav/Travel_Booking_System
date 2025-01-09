<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hotel Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
            background-image: url('img/hotels_list.jpg'); /* Replace with your actual image URL */
            background-size: cover;
            background-position: fill;
            background-attachment: fixed; /* Keep background still */
        }
        .container {
            margin: 20px auto;
            max-width: 1200px;
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .hotel-card {
            background: rgba(255, 255, 255, 0.8); /* Translucent background */
            border-radius: 8px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            width: 300px;
            flex-grow: 1;
            display: flex;
            flex-direction: column;
        }
        .hotel-card img {
            width: 100%;
            height: 200px;
            object-fit: cover;
        }
        .hotel-card-content {
            padding: 15px;
            display: flex;
            flex-direction: column;
            flex-grow: 1;
        }
        .hotel-card h2 {
            font-size: 18px;
            margin: 0 0 10px;
        }
        .hotel-card p {
            margin: 5px 0;
            color: #555;
        }
        .hotel-card-footer {
            padding: 10px;
            background: #007bff; /* Blue background */
            color: white;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.3s;
        }
        .hotel-card-footer:hover {
            background: #0056b3; /* Darker blue on hover */
        }
    </style>
    <script>
        function displayHotels(hotelData) {
            const container = document.getElementById('hotel-container');
            container.innerHTML = ''; // Clear existing content

            hotelData.forEach(hotel => {
                const hotelCard = document.createElement('div');
                hotelCard.className = 'hotel-card';

                const hotelImage = document.createElement('img');
                hotelImage.src = hotel.photoUrls ? hotel.photoUrls[0].replace('square60', 'square600') : 'default-image.jpg';
                hotelImage.alt = hotel.name || 'Hotel Image';
                hotelCard.appendChild(hotelImage);

                const hotelContent = document.createElement('div');
                hotelContent.className = 'hotel-card-content';

                const hotelName = document.createElement('h2');
                hotelName.textContent = hotel.name || 'N/A';
                hotelContent.appendChild(hotelName);

                const checkinDate = document.createElement('p');
                checkinDate.textContent = `Check-in Date: ${hotel.checkinDate || 'N/A'}`;
                hotelContent.appendChild(checkinDate);

                const rankingPosition = document.createElement('p');
                rankingPosition.textContent = `Ranking Position: ${hotel.rankingPosition != null ? hotel.rankingPosition : 'N/A'}`;
                hotelContent.appendChild(rankingPosition);

                const priceBreakdown = document.createElement('p');
                const grossPrice = hotel.priceBreakdown && hotel.priceBreakdown.grossPrice && hotel.priceBreakdown.grossPrice.amountRounded
                    ? hotel.priceBreakdown.grossPrice.amountRounded
                    : 'N/A';
                priceBreakdown.textContent = `Price: ${grossPrice}`;
                hotelContent.appendChild(priceBreakdown);

                hotelCard.appendChild(hotelContent);

                const hotelFooter = document.createElement('a');
                hotelFooter.href = '#'; // Replace with the actual booking link
                hotelFooter.className = 'hotel-card-footer';
                hotelFooter.textContent = 'Book Now';
                hotelCard.appendChild(hotelFooter);

                container.appendChild(hotelCard);
            });
        }

        function parseHotelResults() {
            const hotelResultsElement = document.getElementById('hotelResults');
            if (hotelResultsElement) {
                try {
                    const hotelResults = JSON.parse(hotelResultsElement.textContent);
                    displayHotels(hotelResults.data);
                } catch (error) {
                    console.error('Error parsing JSON:', error);
                    alert('Error parsing hotel results');
                }
            }
        }

        document.addEventListener('DOMContentLoaded', parseHotelResults);
    </script>
</head>
<body>
    <div class="container" id="hotel-container"></div>
    <pre id="hotelResults" style="display: none;"><%= request.getAttribute("hotelResults") %></pre>
</body>
</html>
