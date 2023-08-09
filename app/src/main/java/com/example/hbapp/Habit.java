package com.example.hbapp;

public class Habit {
    private String title;
    private String description;
    private String id;
    private  String hour;
    private String min;

    public Habit(String id, String title, String description, String hour, String min) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.hour = hour;
        this.min = min;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
