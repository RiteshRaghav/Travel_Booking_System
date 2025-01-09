package com.java.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import com.java.model.FlightQuote;

@WebServlet("/JourneyServlet")
public class JourneyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String departureCity = request.getParameter("departureCity");
        String destinationCity = request.getParameter("destinationCity");
        String departureDate = request.getParameter("departureDate");

        List<FlightQuote> flightQuotes = new ArrayList<>();
        try {
            flightQuotes = getFlightQuotesFromAPI(departureCity, destinationCity, departureDate);
        } catch (InterruptedException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "The request was interrupted. Please try again.");
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "There was an error processing your request. Please try again.");
        }

        // Log the flight quotes for debugging
        System.out.println("Flight Quotes: " + flightQuotes);

        // Set flight quotes as a request attribute
        request.setAttribute("flightQuotes", flightQuotes);

        // Forward to results.jsp
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }

    // Method to fetch flight quotes from API
    private List<FlightQuote> getFlightQuotesFromAPI(String from, String to, String date) throws IOException, InterruptedException {
        List<FlightQuote> flightQuotes = new ArrayList<>();
        String apiUrl = "https://flights-sky.p.rapidapi.com/flights/price-calendar-web?fromEntityId=" + from + "&toEntityId=" + to + "&yearMonth="+date;

        // Create HttpRequest object
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("x-rapidapi-key", "API_KEY")
            .header("x-rapidapi-host", "flights-sky.p.rapidapi.com")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();

        // Send HttpRequest and get HttpResponse
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        if (response.statusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(response.body());
            JSONArray gridArray = jsonResponse.getJSONObject("data").getJSONObject("PriceGrids").getJSONArray("Grid");

            for (int i = 0; i < gridArray.length(); i++) {
                JSONArray dailyQuotes = gridArray.getJSONArray(i);
                for (int j = 0; j < dailyQuotes.length(); j++) {
                    JSONObject dailyQuote = dailyQuotes.getJSONObject(j);
                    boolean directOutboundAvailable = dailyQuote.getBoolean("DirectOutboundAvailable");
                    if (directOutboundAvailable) {
                        JSONObject direct = dailyQuote.getJSONObject("Direct");
                        String price = String.valueOf(direct.getDouble("Price"));
                        String flightCode = "ingo6E" + (flightQuotes.size() + 1) + "-" + date;

                        flightQuotes.add(new FlightQuote(date, price, from, to, flightCode));
                    }
                }
            }
        } else {
            System.out.println("GET request failed: " + response.body());
            // Handle the error as needed
        }

        return flightQuotes;
    }
}
