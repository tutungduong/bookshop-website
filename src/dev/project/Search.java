package dev.project;

import java.util.List;

public interface Search {
    public static List<Room> search(RoomStyle style, Date date, int duration);
}

public class Catalog implements Search {
    private List<Room> rooms;

    public List<Room> search(RoomStyle style, Date date, int duration);
}