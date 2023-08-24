package dev.project;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Random;

public abstract class Person {
    private String name;
    private String address; // tam thoi
    private String email;
    private String phone;
   // private Account account;

//    RANDOM
    protected static Random random = new Random();
    String [] names = {"Tiebout Pryer", "Sergei Whaite", "Brian Blackaby", "Raddy Exroll", "Aeriel Smetoun"};
    String [] addresses = {"49 Carberry Place", "9472 Anhalt Terrace", " 9472 Anhalt Terrace ", "9472 Anhalt Terrace", "9472 Anhalt Terrace"};
    String [] emails = {"ebaudins0@un.org","mfrackiewicz3@boston.com","eriseborough2@google.it","mfrackiewicz3@boston.com","emewsb@blogs.com"};
    String [] phones = {"121-430-4045","959-515-6281","653-105-3097","253-379-0523","200-603-0645"};

    public Person() {
        this.name = names[random.nextInt(names.length -1 )];
        this.address = addresses[random.nextInt(addresses.length -1 )];
        this.email = emails[random.nextInt(emails.length -1 )];
        this.phone = phones[random.nextInt(phones.length -1)];
     //   this.account = account;
    }


}


//public class Guest extends Person {
//    private int totalRoomsCheckedIn;
//
//    public List<RoomBooking> getBookings();
//}

//public class Receptionist extends Person {
//    public List<Member> searchMember(String name);
//    public boolean createBooking();
//}
//
//public class Housekeeper extends Person {
//    public boolean assignToRoom();
//}