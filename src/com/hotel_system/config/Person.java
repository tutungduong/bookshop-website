package com.hotel_system.config;

import com.hotel_system.constants.RoomStatus;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Person {
    private String name;
    private Address address;
    private String email;
    private String phone;
    private Account account;


    String [] names = {"Tiebout Pryer", "Sergei Whaite", "Brian Blackaby", "Raddy Exroll", "Aeriel Smetoun"};
//    String [] addresses = {"49 Carberry Place", "9472 Anhalt Terrace", " 9472 Anhalt Terrace ", "9472 Anhalt Terrace", "9472 Anhalt Terrace"};
    String [] emails = {"ebaudins0@un.org","mfrackiewicz3@boston.com","eriseborough2@google.it","mfrackiewicz3@boston.com","emewsb@blogs.com"};
    String [] phones = {"121-430-4045","959-515-6281","653-105-3097","253-379-0523","200-603-0645"};
    String [] usernames = {"duqfin","mythraspy","efficiencyscatter","efficiencyscatter","efficiencyscatter"};


    protected static Random random = new Random();

    public Person() {
        this.name = names[random.nextInt(names.length -1 )];
        this.address = new Address();
        this.email = emails[random.nextInt(emails.length -1 )];
        this.phone = phones[random.nextInt(phones.length -1)];
        this.account = new Account(usernames[random.nextInt(usernames.length -1)]);
    }


    public Person(String name, Address address, String email, String phone, Account account) {
        this.name = name;
        this.address = new Address();
        this.email = email;
        this.phone = phone;
        this.account = new Account(usernames[random.nextInt(usernames.length - 1)]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
//                ", accountType=" + accountType +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}


class Guest extends Person {
    private int totalRoomsCheckedIn;
    private List<RoomBooking> bookingList;


    public Guest(int totalRoomsCheckedIn) {
        super();
        this.totalRoomsCheckedIn = random.nextInt(1,5);
    }

    public Guest(String name, Address address, String email, String phone, Account account, int totalRoomsCheckedIn) {
        super(name, address, email, phone, account);
        this.totalRoomsCheckedIn = totalRoomsCheckedIn;
    }

    public int getTotalRoomsCheckedIn() {
        return totalRoomsCheckedIn;
    }

    public void setTotalRoomsCheckedIn(int totalRoomsCheckedIn) {
        this.totalRoomsCheckedIn = totalRoomsCheckedIn;
    }

    public List<RoomBooking> getBookings(){
        return bookingList;
    }
    public void setBookingList(List<RoomBooking> bookingList) {
        this.bookingList = bookingList;
    }
}

 class Receptionist extends Person {

     public Receptionist() {
         super();
     }

     public Receptionist(String name, Address address, String email, String phone, Account account) {
         super(name, address, email, phone, account);
     }

     public List<Member> searchMember(String name){
        return  null;
    }
    public boolean createBooking(){
        return false;
    }
}

class Housekeeper extends Person {

    private boolean isCleaningRoom;
    public Housekeeper() {
        super();
        this.isCleaningRoom = false;
    }

    public Housekeeper(String name, Address address, String email, String phone, Account account) {
        super(name, address, email, phone, account);
        this.isCleaningRoom = false;
    }

//    public boolean assignToRoom(Room room) {
//        if(!isCleaningRoom){
//            room.setStatus(RoomStatus.BEING_SERVICED);
//            isCleaningRoom = true;
//            List<RoomHousekeeping> housekeepings = new ArrayList<>();
//            housekeepings.add(new RoomHousekeeping(this));
//            room.setHousekeepingLog(housekeepings);
//            return true;
//        }
//        return false;
//    }
    public boolean assignToRoom(Room room) {
        if (!this.isCleaningRoom) {
            room.setStatus(RoomStatus.BEING_SERVICED);
            this.isCleaningRoom = true;
            List<RoomHousekeeping> housekeepings = new ArrayList<>();
            housekeepings.add(new RoomHousekeeping(this));
            room.setHousekeepingLog(housekeepings);
            room.setStatus((RoomStatus.AVAILABLE));
            return true;
        }
        this.isCleaningRoom = false;
        return false;
}
}