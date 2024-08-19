package edu.gatech.seclass.jobcompare6300;

import java.io.Serializable;

public class Location implements Serializable {
    private String city;
    private String state;

    public Location(String city, String state){
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCompleteLocation() {
        return city + "," + state;
    }
}
