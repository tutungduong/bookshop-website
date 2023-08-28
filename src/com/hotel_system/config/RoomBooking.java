package com.hotel_system.config;

import com.hotel_system.constants.BookingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomBooking {
    private String reservationNumber;
    private LocalDateTime startLocalDateTime;
    private int durationInDays;
    private BookingStatus status;
    private LocalDateTime checkin;
    private LocalDateTime checkout;
    private int guestId;
    private Room room;
    private Invoice invoice;
    private List<Notification> notifications;

//    private static List<RoomBooking> roomBookings;

    
    private static int tempReservationNumber = 1000000;
    private static int id = 1000;

    private Random random = new Random();
    public RoomBooking(){
        ++tempReservationNumber;
        this.reservationNumber = new String(String.valueOf(tempReservationNumber));
        this.startLocalDateTime = LocalDateTime.now();
        this.durationInDays = random.nextInt(1,5);
        this.status = BookingStatus.REQUESTED;
        this.checkin = null;
        this.checkout = null;
        ++id;
        this.guestId = id;
        this.room = new Room();
    }


    @Override
    public String toString() {
        return "RoomBooking{" +
                "reservationNumber='" + reservationNumber + '\'' +
                ", startLocalDateTime=" + startLocalDateTime +
                ", durationInDays=" + durationInDays +
                ", status=" + status +
                ", checkin=" + checkin +
                ", checkout=" + checkout +
                ", guestId=" + guestId +
                ", room=" + room +
                '}';
    }

//    public static RoomBooking fetchDetails(String reservationNumber) {
//          return null;
//    }
public static RoomBooking fetchDetails(String reservationNumber, List<RoomBooking> roomBookings) {
    if (reservationNumber != null && !reservationNumber.isEmpty()) {
        for (RoomBooking booking : roomBookings) {
            if (booking.getReservationNumber().equals(reservationNumber)) {
                return booking;
            }
        }
    }
    return null;
}

    public String getReservationNumber() {
        return reservationNumber;
    }
    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getStartLocalDateTime() {
        return startLocalDateTime;
    }

    public void setStartLocalDateTime(LocalDateTime startLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDateTime checkin) {
        this.checkin = checkin;
    }

    public LocalDateTime getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDateTime checkout) {
        this.checkout = checkout;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
}