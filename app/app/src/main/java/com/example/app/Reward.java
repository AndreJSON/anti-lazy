package com.example.app;

/**
 * Created by andre on 2017-12-10.
 */

public class Reward {
    private String id;
    private String description;
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reward(String id, String description, int points) {
        this.id = id;
        this.description = description;
        this.points = points;
    }
}
