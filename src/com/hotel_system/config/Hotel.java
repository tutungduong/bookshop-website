package com.hotel_system.config;


import java.util.List;

public class Hotel {
    private String name;
    private List<HotelBranch> locations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HotelBranch> getLocations() {
        return locations;
    }

    public void setLocations(List<HotelBranch> locations) {
        this.locations = locations;
    }

    public boolean addLocation(HotelBranch location){
        locations.add(location);
        return true;
    }
}

class HotelBranch {
    private String name;
    private Address location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public List<Room> getRooms(){
        return null;
    }
}