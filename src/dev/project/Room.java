package dev.project;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Room {
    private String roomNumber;
    private Enums.RoomStyle style;
    private Enums.RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;
    private List<RoomKey> keys;
    private List<RoomHousekeeping> housekeepingLog;
    private List<Room> roomList;


    private static int id = 100;
    protected static Random random = new Random();
    public Room(){
        this.roomNumber = getID();
        this.style = Enums.RoomStyle.values()[random.nextInt(Enums.RoomStyle.values().length)];
        this.status = Enums.RoomStatus.values()[random.nextInt(Enums.RoomStatus.values().length)];
        this.bookingPrice = 50 + random.nextDouble() * 150;
        this.isSmoking = random.nextBoolean();
        this.roomList = new ArrayList<>();
        this.keys = new ArrayList<>();
        keys.add(new RoomKey());
    }
//    private String getID(){
//        for (int i = id; i < i*10; i += 100) {
//            for (int j = 0; j < 10; j++) {
//                return new String(String.valueOf(i+j));
//            }
//        }
//        return null;
//    }
    private String getID(){
        ++id;
        return new String(String.valueOf(id));
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Enums.RoomStatus getStatus() {
        return status;
    }

    public boolean isRoomAvailable(String roomNumber){
        Room room = findRoom(roomNumber);
        if(room != null){
            if(room.getStatus() != status.AVAILABLE){
                return true;
            }
        }
        return false;
    }
    private Room findRoom(String roomNumber){
        for(Room room : roomList){
            if(room.getRoomNumber().equals(roomNumber)){
                return room;
            }
        }
        return null;
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
                '}';
    }
}

 class RoomKey {
    private String keyId;
    private String barcode;
    private LocalDateTime issuedAt;
    private boolean isActive;
    private boolean isMaster;

     private int defaultKeyLength = 10;

     private static Set<String> keySet = new HashSet<>();
     private static Set<String> keyCode = new HashSet<>();

     protected static Random random = new Random();
    public RoomKey() {
        this.keyId = generatedKey(keySet);
        this.barcode = generatedBarcode(keyCode);
        this.issuedAt = LocalDateTime.now();
        this.isActive = random.nextBoolean();
        this.isMaster = random.nextBoolean();
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
     public boolean assignRoom(Room room) {
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
 }

 class RoomHousekeeping
{
    private String description;
    private Date startDatetime;
    private int duration;
    private Housekeeper housekeeper;

    public RoomHousekeeping(String description, Date startDatetime, int duration, Housekeeper housekeeper) {
        this.description = description;
        this.startDatetime = startDatetime;
        this.duration = duration;
        this.housekeeper = housekeeper;
    }

    public boolean addHousekeeping(Room room){
        return false;
    }
}