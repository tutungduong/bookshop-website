package com.hotel_system.config;
import java.time.LocalDateTime;
import java.time.LocalTime;


// Cac loai dich vu
public abstract class Service {
    private LocalDateTime issueAt;

    public boolean addInvoiceItem(Invoice invoice){
        return false;
    }

    public LocalDateTime getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(LocalDateTime issueAt) {
        this.issueAt = issueAt;
    }
}




class Amenity extends Service {
//    Amenity Service: This could include services that provide convenience and comfort to hotel guests. It might encompass:
//    Spa and Relaxation: Beauty treatments, steam rooms, massages, and other relaxation therapies.
//    Fitness Center: Equipped gym facilities for guests to exercise and maintain their health.
//    Swimming Pool: Swimming pool facilities for guests to relax in the water.
//    Entertainment Areas: This could involve children's play areas, game rooms, mini-golf, and various entertainment amenities.
    private String name;
    private String description;

    public Amenity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

class RoomService extends Service {
//    Room Service: These are services related to the guest's accommodation:
//
//            24/7 Room Service: Providing food and beverages in the room around the clock.
//    Laundry Service: Laundry, ironing, and clothes cleaning services for guests.
//    Tour and Ticket Services: Assisting guests in booking local tours and attractions.
    private boolean isChargeable;
    private LocalDateTime requestTime;

    public RoomService(boolean isChargeable, LocalDateTime requestTime) {
        this.isChargeable = isChargeable;
        this.requestTime = requestTime;
    }

    public boolean isChargeable() {
        return isChargeable;
    }

    public void setChargeable(boolean chargeable) {
        isChargeable = chargeable;
    }

    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(LocalTime requestTime) {
        this.requestTime = LocalDateTime.from(requestTime);
    }
}

class KitchenService extends Service {
//    Kitchen Service: This likely pertains to culinary services within the hotel:
//
//    Food Services: Offering dishes and beverages from the hotel restaurant or through room service.
//    Breakfast Services: Serving breakfast or brunch to guests.
//    Conference and Event Catering: Providing catering services for conferences, seminars, weddings, and meetings.
    private String description;

    public KitchenService(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}