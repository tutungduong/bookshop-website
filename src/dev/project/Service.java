package dev.project;

public abstract class Service {
    private Date issueAt;

    public boolean addInvoiceItem(Invoice invoice);
}

public class Amenity extends Service {
    private String name;
    private String description;
}

public class RoomService extends Service {
    private boolean isChargeable;
    private Date requestTime;
}

public class KitchenService extends Service {
    private String description;
}