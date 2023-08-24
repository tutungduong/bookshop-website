package dev.project;

import java.lang.reflect.Member;
import java.util.List;

public abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Account account;
}


public class Guest extends Person {
    private int totalRoomsCheckedIn;

    public List<RoomBooking> getBookings();
}

public class Receptionist extends Person {
    public List<Member> searchMember(String name);
    public boolean createBooking();
}

public class Housekeeper extends Person {
    public boolean assignToRoom();
}