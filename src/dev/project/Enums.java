package dev.project;

public class Enums {
    // definition of enumerations used in hotel management system
    enum RoomStyle {
        STANDARD, // THUONG
        DELUXE, // SANG TRONG
        FAMILY_SUITE, // PHONG GIA DINH
        BUSINESS_SUITE // PHONG DOANH NGHIEP
    }

    enum RoomStatus {
        AVAILABLE, // DANG HOAT DONG
        RESERVED, // DA DAT TRUOC
        OCCUPIED,  // DA DUOC THUE
        NOT_AVAILABLE, // KHONG TON TAI
        BEING_SERVICED, // DANG SUA CHUA
        OTHER // KHAC
    }

    enum BookingStatus {
        REQUESTED,
        PENDING,
        CONFIRMED,
        CANCELLED,
        ABANDONED
    }

    enum AccountStatus {
        ACTIVE,
        CLOSED,
        CANCELED,
        BLACKLISTED,
        BLOCKED
    }

    enum AccountType {
        MEMBER,
        GUEST,
        MANAGER,
        RECEPTIONIST
    }

    enum PaymentStatus {
        UNPAID,
        PENDING,
        COMPLETED,
        FILLED,
        DECLINED,
        CANCELLED,
        ABANDONED,
        SETTLING,
        SETTLED,
        REFUNDED
    }
}
