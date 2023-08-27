package com.hotel_system.config;


import java.util.Date;

// Notification is an abstract class
public abstract class Notification {
    private int notificationId;
    // The Date data type represents and deals with both date and time.
    private Date createdOn;
    private String content;

    public abstract void sendNotification(Person person);

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

class SMSNotification extends Notification {

    public void sendNotification(Person person) {
        // functionality
    }


}

class EmailNotification extends Notification {

    public void sendNotification(Person person) {
        // functionality
    }
}