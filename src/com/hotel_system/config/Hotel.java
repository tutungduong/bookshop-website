package com.hotel_system.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hotel {
    private String name;
    private List<HotelBranch> locations;

    public Hotel(String name) {
        this.name = name;
        this.locations = new ArrayList<>();
    }
    public Hotel(String name,List<HotelBranch> locations) {
        this.name = name;
        this.locations = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", locations=" + locations +
                '}';
    }


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
        if(location != null){
            locations.add(location);
            return false;
        }
        return true;
    }
}

class HotelBranch {
    private String name;
    private Address location;

    private Random random = new Random();

    String[] branchNames = {"Downtown Branch", "Beachside Branch", "Mountain View Branch", "Suburbia Branch"};

    public HotelBranch() {
        this.name = branchNames[random.nextInt(branchNames.length - 1)];
        this.location = new Address();
    }

    public HotelBranch(String name, Address location) {
        this.name = name;
        this.location = location;
    }

    @Override
    public String toString() {
        return "HotelBranch{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }

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