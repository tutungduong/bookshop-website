package com.hotel_system.config;
import java.util.Date;
public abstract class Service {
    private Date issueAt;

    public boolean addInvoiceItem(Invoice invoice){
        return false;
    }

    public Date getIssueAt() {
        return issueAt;
    }

    public void setIssueAt(Date issueAt) {
        this.issueAt = issueAt;
    }
}

class Amenity extends Service {
    private String name;
    private String description;

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
    private boolean isChargeable;
    private Date requestTime;

    public boolean isChargeable() {
        return isChargeable;
    }

    public void setChargeable(boolean chargeable) {
        isChargeable = chargeable;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }
}

class KitchenService extends Service {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}