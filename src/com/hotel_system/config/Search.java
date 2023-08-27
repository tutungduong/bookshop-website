package com.hotel_system.config;

import com.hotel_system.constants.RoomStyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public interface Search {
    public static List<Room> search(RoomStyle style, Date date, int duration){
        return new ArrayList<>();
    }
}

class Catalog implements Search {
    private List<Room> rooms;

    public List<Room> search(RoomStyle style, Date date, int duration){
        return new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}