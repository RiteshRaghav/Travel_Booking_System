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
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Servlet implementation class CabServlet
 */
@WebServlet("/CabServlet")
public class CabServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CabServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form parameters
        String pickUpLocation = request.getParameter("pickUpLocation");
        String dropOffLocation = request.getParameter("dropOffLocation");
        String pickUpDateStr = request.getParameter("pickUpDate");
        String pickUpTime = request.getParameter("pickUpTime");
        String dropOffDateStr = request.getParameter("dropOffDate");
        String dropOffTime = request.getParameter("dropOffTime");

        // Validate dates and set format to YYYY-MM-DD
        LocalDate pickUpDate = validateDateFormat(pickUpDateStr);
        LocalDate dropOffDate = validateDateFormat(dropOffDateStr);

        // Fetch entity_id for pick-up location
        String pickUpEntityId = fetchEntityId(pickUpLocation);
        if (pickUpEntityId != null) {
            // Fetch entity_id for drop-off location
            String dropOffEntityId = fetchEntityId(dropOffLocation);
            if (dropOffEntityId != null && pickUpDate != null && dropOffDate != null) {
                // Search cars using the retrieved entity_ids
                String carSearchResponse = searchCars(pickUpEntityId, dropOffEntityId, pickUpDate.toString(), dropOffDate.toString(), pickUpTime, dropOffTime);
                // Debug Statement
                System.out.println("Car Search Response: " + carSearchResponse);
                // Set the car search response as a request attribute
                request.setAttribute("carSearchResults", carSearchResponse);
                // Forward to JSP
                request.getRequestDispatcher("Cabresults.jsp").forward(request, response);
            } else {
                response.getWriter().write("Failed to fetch drop-off location entity ID or invalid date format.");
            }
        } else {
            response.getWriter().write("Failed to fetch pick-up location entity ID.");
        }
    }

    // Method to validate and parse date to YYYY-MM-DD format
    private LocalDate validateDateFormat(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            System.out.println("Invalid date format: " + dateStr);
            return null;
        }
    }

    // Method to fetch entity_id from the first API
    private String fetchEntityId(String query) {
        String entityId = null;
        try {
            String apiUrl = "https://flights-sky.p.rapidapi.com/cars/auto-complete?query=" + URLEncoder.encode(query, StandardCharsets.UTF_8.name());
            String apiResponse = callApi(apiUrl, "Api_key", "flights-sky.p.rapidapi.com");

            JSONObject jsonObject = new JSONObject(apiResponse); // Requires JSON library
            if (jsonObject.has("data") && jsonObject.get("data") instanceof JSONArray) {
                JSONArray data = jsonObject.getJSONArray("data");
                if (data.length() > 0) {
                    entityId = data.getJSONObject(0).getString("entity_id"); // Extract first entity_id
                }
            } else {
                System.out.println("Expected 'data' to be a JSONArray, but got: " + jsonObject.get("data").getClass().getName());
                throw new org.json.JSONException("Expected 'data' to be a JSONArray");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityId;
    }

    // Method to search cars using the entity_ids and correct date format
    private String searchCars(String pickUpEntityId, String dropOffEntityId, String pickUpDate, String dropOffDate, String pickUpTime, String dropOffTime) {
        String apiUrl = "https://flights-sky.p.rapidapi.com/cars/search?pickUpEntityId=" + pickUpEntityId +
                "&pickUpDate=" + pickUpDate + "&pickUpTime=" + pickUpTime +
                "&dropOffDate=" + dropOffDate + "&dropOffTime=" + dropOffTime + "&dropOffEntityId=" + dropOffEntityId;
        return callApi(apiUrl, "API_key", "flights-sky.p.rapidapi.com");
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

            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
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
}
