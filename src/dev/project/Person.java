package dev.project;

import javax.swing.text.View;
import java.awt.print.Book;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

interface BookingAction{
//    Book room: To book a room in the hotel
    boolean bookRoom();
//    Update booking: To update a room booking in the hotel
    boolean updateBooking();
//    Login/Logout: To log in and out of the hotel management system
    boolean logIn();
//    Cancel booking: To cancel a room booking in the hotel
    boolean cancelBooking();
//    View booking: To view and verify a room booking
    boolean viewBookingDetails();
//    Print booking: To print booking details from the hotel management system
    boolean printBookingDetails();
//    Search room/booking: To search for a room or a booking in the hotel management system
    boolean searchRoomOrBooking();
//    View account: To view account details and booking status
    boolean viewAccount();
//    Register new account: To register a new account for new guests
    boolean registerNewAccount(String username);
}

public abstract class Person{
    private String name;
//    private String address; // tam thoi
    private Address address;
    private String email;
    private String phone;
//    private Account account;
    protected Enums.AccountType accountType;

//    RANDOM
    String [] names = {"Tiebout Pryer", "Sergei Whaite", "Brian Blackaby", "Raddy Exroll", "Aeriel Smetoun"};
    String [] addresses = {"49 Carberry Place", "9472 Anhalt Terrace", " 9472 Anhalt Terrace ", "9472 Anhalt Terrace", "9472 Anhalt Terrace"};
    String [] emails = {"ebaudins0@un.org","mfrackiewicz3@boston.com","eriseborough2@google.it","mfrackiewicz3@boston.com","emewsb@blogs.com"};
    String [] phones = {"121-430-4045","959-515-6281","653-105-3097","253-379-0523","200-603-0645"};
    String [] usernames = {"mythraspy","mythraspy","efficiencyscatter","efficiencyscatter","efficiencyscatter"};

    protected static Random random = new Random();
    public Person(String name, Address address, String email, String phone) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
//        this.account = new Account(usernames[random.nextInt(usernames.length - 1)]);
        this.accountType = accountType.GUEST;
    }

    public Person() {
        this.name = names[random.nextInt(names.length -1 )];
//        this.address = addresses[random.nextInt(addresses.length -1 )];
        this.address = new Address();
        this.email = emails[random.nextInt(emails.length -1 )];
        this.phone = phones[random.nextInt(phones.length -1)];
//        this.account = new Account(usernames[random.nextInt(usernames.length -1)]);
        this.accountType = accountType.GUEST;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType=" + accountType +
                '}';
    }
}


class Guest extends Person implements BookingAction{

    private int totalRoomsCheckedIn;
    private ArrayList<Account> accounts;
    public Guest(){
        super();
        this.totalRoomsCheckedIn = random.nextInt(1,5);
        this.accounts = new ArrayList<>();
    }
    public List<RoomBooking> getBookings(){
        return null;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "totalRoomsCheckedIn=" + totalRoomsCheckedIn +
                ", accounts=" + accounts +
                "} " + super.toString();
    }

    @Override
    public boolean bookRoom() {
        return false;
    }

    @Override
    public boolean updateBooking() {
        return false;
    }

    @Override
    public boolean logIn() {
        return false;
    }

    @Override
    public boolean cancelBooking() {
        return false;
    }

    @Override
    public boolean viewBookingDetails() {
        return false;
    }

    @Override
    public boolean printBookingDetails() {
        return false;
    }

    @Override
    public boolean searchRoomOrBooking() {
        return false;
    }

    @Override
    public boolean viewAccount() {
        return false;
    }
    //    Register new account: To register a new account for new guests
    @Override
    public boolean registerNewAccount(String username) {
        Account account = findAccount(username);
        if(account == null){
            this.accountType = accountType.MEMBER;
            return accounts.add(new Account(username));
        }
        return false;
    }

    // Find an account that used to exist
    private Account findAccount(String username){
        for(Account account : accounts){
            if(account.getId().equals(username)){
                return account;
            }
        }
        return null;
    }
}

 class Receptionist extends Person implements BookingAction {
    public List<Member> searchMember(String name){
        return null;
    }
    public boolean createBooking(){
        return false;
    }

     @Override
     public boolean bookRoom() {
         return false;
     }

     @Override
     public boolean updateBooking() {
         return false;
     }

     @Override
     public boolean logIn() {
         return false;
     }

     @Override
     public boolean cancelBooking() {
         return false;
     }

     @Override
     public boolean viewBookingDetails() {
         return false;
     }

     @Override
     public boolean printBookingDetails() {
         return false;
     }

     @Override
     public boolean searchRoomOrBooking() {
         return false;
     }

     @Override
     public boolean viewAccount() {
         return false;
     }

     @Override
     public boolean registerNewAccount(String username) {
         Guest guest = new Guest();
         return guest.registerNewAccount(username);
     }
 }

 class Housekeeper extends Person {
    public boolean assignToRoom(){
        return false;
    }
}