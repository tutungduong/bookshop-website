package com.hotel_system.config;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Khanh An");
        List<HotelBranch> hotelBranches = new ArrayList<>();
        for(int i = 0 ; i < 4 ; ++i){
            hotelBranches.add(new HotelBranch());
            hotel.addLocation(hotelBranches.get(i));
        }
        System.out.println(hotel);
    }
}

