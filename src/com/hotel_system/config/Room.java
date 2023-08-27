package com.hotel_system.config;

import com.hotel_system.constants.RoomStatus;
import com.hotel_system.constants.RoomStyle;

import java.time.LocalDateTime;
import java.util.*;

public class Room {
    private String roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;
    private List<RoomKey> keys;
    private List<RoomHousekeeping> housekeepingLog;
    // temp
    private static int id = 100;
    private List<Room> availableRooms;

    public static Random random = new Random();

    public Room(){
        this.roomNumber = getID();
        this.style = style.STANDARD;
//        this.style = RoomStyle.values()[random.nextInt(RoomStyle.values().length)];
        this.status = status.AVAILABLE;
        this.bookingPrice = Math.round(50 + random.nextDouble() * 150);
        this.isSmoking = false;
        this.keys = null;
        this.housekeepingLog = null;
        this.availableRooms = new ArrayList<>();
    }
    private String getID(){
        ++id;
        return new String(String.valueOf(id));
    }


    public boolean isRoomAvailable() {
        if(this.getStatus().equals(RoomStatus.NOT_AVAILABLE) || this.getStatus() == null){
            return false;
        }
        return true; // Room does not exist
    }
    public boolean checkin(){
        return false;
    }
    public boolean checkout(){
        return false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", style=" + style +
                ", status=" + status +
                ", bookingPrice=" + bookingPrice +
                ", isSmoking=" + isSmoking +
                ", keys=" + keys +
                ", housekeepingLog=" + housekeepingLog +
                '}';
    }

    public Room(String roomNumber, RoomStyle style, RoomStatus status, double bookingPrice, boolean isSmoking) {
        this.roomNumber = roomNumber;
        this.style = style;
        this.status = status;
        this.bookingPrice = bookingPrice;
        this.isSmoking = isSmoking;
        this.keys = new ArrayList<>();
        this.housekeepingLog = new ArrayList<>();
    }


    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomStyle getStyle() {
        return style;
    }

    public void setStyle(RoomStyle style) {
        this.style = style;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public double getBookingPrice() {
        return bookingPrice;
    }

    public void setBookingPrice(double bookingPrice) {
        this.bookingPrice = bookingPrice;
    }

    public boolean isSmoking() {
        return isSmoking;
    }

    public void setSmoking(boolean smoking) {
        isSmoking = smoking;
    }

    public List<RoomKey> getKeys() {
        return keys;
    }

    public void setKeys(List<RoomKey> keys) {
        this.keys = keys;
    }

    public List<RoomHousekeeping> getHousekeepingLog() {
        return housekeepingLog;
    }

    public void setHousekeepingLog(List<RoomHousekeeping> housekeepingLog) {
        this.housekeepingLog = housekeepingLog;
    }
//    public List<Room> search(RoomStyle style, Date startDate, int duration) {
//        // return all rooms with the given style and availability
//        return new ArrayList<>();
//    }
}


class RoomKey {
    private String keyId;
    private String barcode;
    private LocalDateTime issuedAt;
    private boolean isActive;
    private boolean isMaster;

    private int defaultKeyLength = 10;
//    private static Set<String> keySet = new HashSet<>();
//    private static Set<String> keyCode = new HashSet<>();

    public RoomKey() {
        // Chưa test xem có trùng key không nha.
//        this.keyId = generatedKey(keySet);
//        this.barcode = generatedBarcode(keyCode);
        this.keyId = randomKey(defaultKeyLength);
        this.barcode = randomBarcode(defaultKeyLength+3);
        this.issuedAt = LocalDateTime.now();
        this.isActive = true;
        this.isMaster = true;
    }

    private String generatedKey(Set<String> keySet){
        String key;
        do {
            key = randomKey(defaultKeyLength);
        } while (keySet.contains(key));
        keySet.add(key);
        return key;
    }
    private String generatedBarcode(Set<String> keyCode){
        String key;
        do {
            key = randomBarcode(defaultKeyLength);
        } while (keyCode.contains(key));
        keyCode.add(key);
        return key;
    }
    private String randomKey(int length){
        String passwordSet="ABCDEFGHIJKLMNOPQRSTUWXYZ0123456789";
        char[] password = new char[length];
        for(int i = 0 ; i < length ; ++i){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }
    private String randomBarcode(int length){
        String passwordSet="0123456789";
        char[] password = new char[length];
        for(int i = 0 ; i < length ; ++i){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public boolean assignRoom(Room room){
      if(room.isRoomAvailable() && room.getKeys() == null){
          List<RoomKey> keys = new ArrayList<>();
          keys.add(new RoomKey()); // Create a new RoomKey and add it to the list of keys
          room.setKeys(keys); // Assign a list of keys to a room
          return true;
      }
      return false;
    }

    @Override
    public String toString() {
        return "RoomKey{" +
                "keyId='" + keyId + '\'' +
                ", barcode='" + barcode + '\'' +
                ", issuedAt=" + issuedAt +
                ", isActive=" + isActive +
                ", isMaster=" + isMaster +
                '}';
    }
    public RoomKey(String keyId, String barcode, LocalDateTime issuedAt, boolean isActive, boolean isMaster) {
        this.keyId = keyId;
        this.barcode = barcode;
        this.issuedAt = issuedAt;
        this.isActive = isActive;
        this.isMaster = isMaster;
    }


    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }
}

class RoomHousekeeping
{
    private String description;
    private LocalDateTime startDatetime;
    private int duration;
    private Housekeeper housekeeper;

    private Random random = new Random();

    String[] descriptions = {
            "Thoroughly cleaned and sanitized the room.",
            "Changed bed linens and towels.",
            "Vacuumed and dusted the entire room.",
            "Cleaned the bathroom fixtures and surfaces.",
            "Wiped down all surfaces and furniture.",
            "Replenished amenities and supplies.",
            "Checked and restocked the minibar.",
            "Removed trash and disposed of it properly."
    };

    public RoomHousekeeping(Housekeeper housekeeper) {
        this.description = descriptions[random.nextInt(descriptions.length-1)];
        this.startDatetime = LocalDateTime.now();
        this.duration = random.nextInt(10,50);
        this.housekeeper = housekeeper;
    }

    public RoomHousekeeping(String description, LocalDateTime startDatetime, int duration, Housekeeper housekeeper) {
        this.description = description;
        this.startDatetime = startDatetime;
        this.duration = duration;
        this.housekeeper = housekeeper;
    }


//    public boolean addHousekeeping(Room room){
//        if(room != null){
//            if(room.isRoomAvailable() && room.getStatus() != RoomStatus.BEING_SERVICED){
//                List<Housekeeper> housekeepers = new ArrayList<>();
//                housekeepers.add(new Housekeeper());
//
//                // Assign the housekeeper to the room
//                for (Housekeeper housekeeper: housekeepers) {
//                    if(housekeeper.assignToRoom(room)){
//                        room.setStatus(RoomStatus.AVAILABLE);
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
public boolean addHousekeeping(Room room, Housekeeper housekeeper) {
    if (room != null && room.isRoomAvailable() && room.getStatus() != RoomStatus.BEING_SERVICED) {
        if (housekeeper.assignToRoom(room)) {
            room.setStatus(RoomStatus.AVAILABLE);
            return true;
        }
    }
    return false;
}

    @Override
    public String toString() {
        return "\nRoomHousekeeping{" +
                "description='" + description + '\'' +
                ", startDatetime=" + startDatetime +
                ", duration=" + duration +
                ", housekeeper=" + housekeeper +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Housekeeper getHousekeeper() {
        return housekeeper;
    }

    public void setHousekeeper(Housekeeper housekeeper) {
        this.housekeeper = housekeeper;
    }
}
