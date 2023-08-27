package com.hotel_system.config;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Housekeeper> housekeepers = new ArrayList<>();
        for(int i = 0 ; i < 3 ; ++i){
            rooms.add(new Room());
            System.out.println(rooms.get(i).toString());
        }
        for(int i = 0 ; i <3 ; ++i){
            housekeepers.add(new Housekeeper());
            System.out.println(housekeepers.get(i).toString());
        }
        Housekeeper housekeeper = null;
        housekeepers.get(0).assignToRoom(rooms.get(0));
        System.out.println(rooms.get(0));
        RoomHousekeeping housekeeping = new RoomHousekeeping(housekeepers.get(1));
        housekeeping.addHousekeeping(rooms.get(1),housekeepers.get(1));
        System.out.println(rooms.get(1));
    }
}
