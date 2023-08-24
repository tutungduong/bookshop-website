package dev.project;

import java.util.Random;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String country;

    String [] streets = {"Charing Cross","Shelley","Springs","Coolidge","Bashford","Knutson","Linden","Bluejay","Pennsylvania","Ilene","Washington","East"};
    String [] citys = {"Banff","Hanna","Sylvan Lake","Malaga","Edson","Swan Hills","Camrose","Granada","Stony Plain","Okotoks","Birmingham","Lethbridge","Beaverlodge"};
    String [] states = {"Andalucia","Alberta","Bayern","Alabama"};
    int [] zipCodes = {1440,176,279,3854,820,7, 78, 656, 5, 322, 48, 47493};
    String [] countrys = {"United States","Canada","Spain", "Germany"};
    
    public Address(String streetAddress, String city, String state, int zipCode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
// Generate a random password
protected static Random random = new Random();
    public Address() {
        this.streetAddress = streets[random.nextInt(streets.length -1 )];
        this.city = citys[random.nextInt(citys.length -1 )];;
        this.state = states[random.nextInt(states.length -1 )];;
        this.zipCode = zipCodes[random.nextInt(zipCodes.length -1 )];;
        this.country = countrys[random.nextInt(countrys.length -1 )];;
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
}