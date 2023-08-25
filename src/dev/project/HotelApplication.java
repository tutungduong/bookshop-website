package dev.project;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelApplication {
    public static void main(String[] args) {

//
//        ArrayList<Guest> guests = new ArrayList<>();
//
//        for(int i = 0 ; i < 5 ; ++i){
//            guests.add(new Guest());
//            System.out.println(guests.get(i).toString());
//        }
//        guests.get(0).registerAccount("duong");
//        System.out.println(guests.get(0).toString());
//        guests.get(1).registerAccount("khanh");
//        System.out.println(guests.get(1).toString());


//        Hotel myHotel = new Hotel("My Awesome Hotel");
//
//        // Create hotel branches
//        HotelBranch branch1 = new HotelBranch();
//        HotelBranch branch2 = new HotelBranch();
//        HotelBranch branch3 = new HotelBranch();
//        HotelBranch branch4 = new HotelBranch();
//        HotelBranch branch5 = new HotelBranch();
//
//        // Add branches to the hotel
//        myHotel.addLocation(branch1);
//        myHotel.addLocation(branch2);
//        myHotel.addLocation(branch3);
//
//        // Display hotel information
//        System.out.println("Hotel Name: " + myHotel.getName());
//        System.out.println("Hotel Locations: ");
//        for (HotelBranch branch : myHotel.getLocations()) {
//            System.out.println(branch);
//        }
        Hotel myHotel = new Hotel("Khach san");

        ArrayList<HotelBranch> hotelBranches = new ArrayList<>();
        for(int i = 0 ; i < 10 ; ++i){
            hotelBranches.add(new HotelBranch());
            myHotel.addLocation(hotelBranches.get(i));
        }
        // Display hotel information
        System.out.println("Hotel Name: " + myHotel.getName());
        System.out.println("Hotel Locations: ");
        for (HotelBranch branch : myHotel.getLocations()) {
            System.out.println(branch);
        }
    }
}
