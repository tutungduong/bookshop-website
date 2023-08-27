package com.hotel_system.config;

import java.util.Random;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String country;



    // Generate random for some attributes

    String [] streets = {"Charin Cross","Shelley","Springs","Coolidge","Bashford","Knutson","Linden","Bluejay","Pennsylvania","Ilene","Washington","East"};
    String [] citys = {"Ban","Hanna","Sylvan Lake","Malaga","Edson","Swan Hills","Camrose","Granada","Stony Plain","Okotoks","Birmingham","Lethbridge","Beaverlodge"};
    String [] states = {"Anda","Alberta","Bayern","Alabama"};
    int [] zipCodes = {1440,176,279,3854,820,7, 78, 656, 5, 322, 48, 47493};
    String [] countrys = {"United States","Canada","Spain", "Germany"};

    protected static Random random = new Random();
    public Address() {
        this.streetAddress = streets[random.nextInt(streets.length -1 )];
        this.city = citys[random.nextInt(citys.length -1 )];;
        this.state = states[random.nextInt(states.length -1 )];;
        this.zipCode = zipCodes[random.nextInt(zipCodes.length -1 )];;
        this.country = countrys[random.nextInt(countrys.length -1 )];;
    }

//    ---------------------------------------------------------------------------------------


    // Constructor
    public Address(String streetAddress, String city, String state, int zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                '}';
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}