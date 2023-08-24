package dev.project;

import java.time.LocalDate;  // For representing date without time
import java.time.LocalDateTime;  // For representing date with time
import java.util.Date;
import java.util.List;

public class RoomBooking {
    private String reservationNumber;
    private Date startDate;
    private int durationInDays;
    private Enums.BookingStatus status;
    private Date checkin;
    private Date checkout;

    private int guestId;
    private Room room;
    private Invoice invoice;
    private List<Notification> notifications;

    public static RoomBooking fectchDetails(String reservationNumber){
        RoomBooking roomBooking = new RoomBooking();
        return roomBooking;
    }
}