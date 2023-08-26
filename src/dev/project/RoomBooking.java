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


    public RoomBooking(String reservationNumber, Date startDate, int durationInDays, Enums.BookingStatus status, Date checkin, Date checkout, int guestId, Room room, Invoice invoice, List<Notification> notifications) {
        this.reservationNumber = reservationNumber;
        this.startDate = startDate;
        this.durationInDays = durationInDays;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.guestId = guestId;
        this.room = room;
        this.invoice = invoice;
        this.notifications = notifications;
    }

    public static RoomBooking fectchDetails(String reservationNumber){
            return null;
    }
}