package dev.project;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Random;

public  class Person {
    private String name;
//    private String address; // tam thoi
    private Address address;
    private String email;
    private String phone;
    private Account account;

//    RANDOM
    protected static Random random = new Random();
    String [] names = {"Tiebout Pryer", "Sergei Whaite", "Brian Blackaby", "Raddy Exroll", "Aeriel Smetoun"};
    String [] addresses = {"49 Carberry Place", "9472 Anhalt Terrace", " 9472 Anhalt Terrace ", "9472 Anhalt Terrace", "9472 Anhalt Terrace"};
    String [] emails = {"ebaudins0@un.org","mfrackiewicz3@boston.com","eriseborough2@google.it","mfrackiewicz3@boston.com","emewsb@blogs.com"};
    String [] phones = {"121-430-4045","959-515-6281","653-105-3097","253-379-0523","200-603-0645"};
    String [] usernames = {"mythraspy","mythraspy","efficiencyscatter","efficiencyscatter","efficiencyscatter"};

    public Person() {
        this.name = names[random.nextInt(names.length -1 )];
//        this.address = addresses[random.nextInt(addresses.length -1 )];
        this.address = new Address();
        this.email = emails[random.nextInt(emails.length -1 )];
        this.phone = phones[random.nextInt(phones.length -1)];
        this.account = new Account(usernames[random.nextInt(usernames.length -1)]);
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nAddress: " + address.toString() +
                "\nEmail: " + email +
                "\nPhone: " + phone +
                "\nAccount: " + account.toString();
    }
}


public class Guest extends Person {
    private int totalRoomsCheckedIn;

    public List<RoomBooking> getBookings(){

    }
}

//public class Receptionist extends Person {
//    public List<Member> searchMember(String name);
//    public boolean createBooking();
//}
//
public class Housekeeper extends Person {
    public boolean assignToRoom(){

    }
}