package com.java.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@WebServlet("/HotelServlet")
public class HotelServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HotelServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form inputs
        String hotelCity = request.getParameter("hotelCity");
        String checkinDate = request.getParameter("checkinDate");
        String checkoutDate = request.getParameter("checkoutDate");

        // Log the retrieved parameters
//        System.out.println("hotelCity: " + hotelCity);
//        System.out.println("Check-in Date: " + checkinDate);
//        System.out.println("Check-out Date: " + checkoutDate);

        // Validate dates and set default if null
        if (checkinDate == null || checkinDate.isEmpty()) {
            checkinDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        if (checkoutDate == null || checkoutDate.isEmpty()) {
            checkoutDate = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        // Ensure parameters are not null before encoding
        String encodedHotelCity = "";
        String encodedCheckinDate = "";
        String encodedCheckoutDate = "";
        try {
            encodedHotelCity = (hotelCity != null) ? URLEncoder.encode(hotelCity, StandardCharsets.UTF_8.name()) : "";
            encodedCheckinDate = (checkinDate != null) ? encodeDate(checkinDate) : "";
            encodedCheckoutDate = (checkoutDate != null) ? encodeDate(checkoutDate) : "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Step 1: Call First API to get location details
        String locationApiResponse = callApi(
            "https://booking-data.p.rapidapi.com/booking/autocomplete?location=" + encodedHotelCity + "&language_code=en-us",
            "API_KEY", // Replace with your key
            "booking-data.p.rapidapi.com"
        );

        // Print the response for debugging
      //  System.out.println("Location API Response: " + locationApiResponse);

        // Extract label from the response
        String locationLabel = extractLabel(locationApiResponse); // Custom method

        // Step 2: Call Second API to get hotel search results
        String hotelApiResponse = callApi(
            "https://booking-data.p.rapidapi.com/booking-app/search/by-location?location=" + URLEncoder.encode(locationLabel, StandardCharsets.UTF_8.name()) +
            "&checkin_date=" + encodedCheckinDate + "&checkout_date=" + encodedCheckoutDate +
            "&units=metric&temperature_unit=c",
            "API_KEY", // Replace with your key
            "booking-data.p.rapidapi.com"
        );

        // Print the hotel API response for debugging
      //  System.out.println("Hotel API Response: " + hotelApiResponse);

        // Step 3: Send response back to JSP
        request.setAttribute("hotelResults", hotelApiResponse); // Send JSON response to JSP
        request.getRequestDispatcher("hotelResults.jsp").forward(request, response); // Forward to JSP
    }

    // Method to call API and return the response as a String
    private String callApi(String apiUrl, String apiKey, String apiHost) {
        StringBuilder response = new StringBuilder();
        BufferedReader in = null;
        HttpURLConnection conn = null;
        try {
            URI uri = new URI(apiUrl); // Use URI to create URL
            conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("x-rapidapi-key", apiKey);
            conn.setRequestProperty("x-rapidapi-host", apiHost);

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
                if (conn != null) conn.disconnect();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response.toString();
    }

    // Method to extract the label from the first API response
    private String extractLabel(String apiResponse) {
        String label = "";
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(apiResponse); // Requires JSON library
            if (jsonObject.has("data") && jsonObject.get("data") instanceof org.json.JSONArray) {
                org.json.JSONArray locations = jsonObject.getJSONArray("data");
                if (locations.length() > 0) {
                    label = locations.getJSONObject(0).getString("label"); // Extract first label
                }
            } else {
                System.out.println("Expected 'data' to be a JSONArray, but got: " + jsonObject.get("data").getClass().getName());
                throw new org.json.JSONException("Expected 'data' to be a JSONArray");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return label;
    }

    // Method to encode date to YYYY-MM-DD format
    private String encodeDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return URLEncoder.encode(parsedDate.toString(), StandardCharsets.UTF_8.name());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + date);
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
