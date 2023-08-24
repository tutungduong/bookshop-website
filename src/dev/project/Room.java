//package dev.project;
//
//import java.util.List;
//
//public class Room {
//    private String roomNumber;
//    private RoomStyle style;
//    private RoomStatus status;
//    private double bookingPrice;
//    private boolean isSmoking;
//    private List<RoomKey> keys;
//    private List<RoomHousekeeping> housekeepingLog;
//
//    public boolean isRoomAvailable();
//    public boolean checkin();
//    public boolean checkout();
//}
//
//public class RoomKey {
//    private String keyId;
//    private String barcode;
//    private Date issuedAt;
//    private boolean isActive;
//    private boolean isMaster;
//
//    public boolean assignRoom(Room room);
//}
//
//public class RoomHousekeeping
//{
//    private String description;
//    private Date startDatetime;
//    private int duration;
//    private Housekeeper housekeeper;
//
//    public boolean addHousekeeping(Room room);
//}