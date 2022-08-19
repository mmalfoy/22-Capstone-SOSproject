package com.example.sosproject;

public class BoardingInfo {
    private int id;
    private String date;
    private String time;
    private int start_s;
    private int end_s;
    private int fare;
    private int total_fare;

    public BoardingInfo(int id, String date, String time, int start_s, int end_s, int fare, int total_fare) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.start_s = start_s;
        this.end_s = end_s;
        this.fare = fare;
        this.total_fare = total_fare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStart() {
        return start_s;
    }

    public void setStart(int start) {
        this.start_s = start;
    }

    public int getEnd() {
        return end_s;
    }

    public void setEnd(int end) {
        this.end_s = end;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getTotal_fare() {
        return total_fare;
    }

    public void setTotal_fare(int total_fare) {
        this.total_fare = total_fare;
    }
}
