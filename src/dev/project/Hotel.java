package dev.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class HotelBranch {
    //Hotel branch name
    private String name;
    private Address location;
    protected static Random random = new Random();

    String[] branchNames = {"Downtown Branch", "Beachside Branch", "Mountain View Branch", "Suburbia Branch"};

    public HotelBranch() {
        this.name = branchNames[random.nextInt(branchNames.length -1 )];
        this.location  = new Address();
    }

    public String getName() {
        return name;
    }

    public Address getLocation() {
        return location;
    }

//    public List<Room> getRooms(){
//
//    }


    @Override
    public String toString() {
        return "HotelBranch{" +
                "name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
}

public class Hotel {
    //Hotel name
    private String name;
    private List<HotelBranch> locations;

    public Hotel(String name) {
        this.name = name;
        this.locations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<HotelBranch> getLocations() {
        return locations;
    }

    public boolean addLocation(HotelBranch location){
        HotelBranch hotelBranch = findLocationByName(location.getName());
        if(hotelBranch == null){
           return locations.add(location);
        }
        return false;
    }

    private HotelBranch findLocationByName(String branchName) {
        for (HotelBranch l : locations) {
            if (l.getName().equals(branchName)) {
                return l;
            }
        }
        return null;
    }

    private HotelBranch findLocationByLocation(Address address) {
        for (HotelBranch l : locations) {
            if (l.getLocation().equals(address)) {
                return l;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", locations=" + locations +
                '}';
    }
}
