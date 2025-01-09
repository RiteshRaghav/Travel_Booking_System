package com.java.model;

public class FlightQuote {
    private String date;
    private String price;
    private String from;
    private String to;
    private String flightCode;

    // Constructor
    public FlightQuote(String date, String price, String from, String to, String flightCode) {
        this.date = date;
        this.price = price;
        this.from = from;
        this.to = to;
        this.flightCode = flightCode;
    }

    // Getters and Setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    // toString() method for debugging purposes
    @Override
    public String toString() {
        return "FlightQuote{" +
               "date='" + date + '\'' +
               ", price='" + price + '\'' +
               ", from='" + from + '\'' +
               ", to='" + to + '\'' +
               ", flightCode='" + flightCode + '\'' +
               '}';
    }
}
