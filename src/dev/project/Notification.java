package dev.project;

import java.util.Date;

// Notification is an abstract class
public abstract class Notification {
    private int notificationId;
    // The Date data type represents and deals with both date and time.
    private Date createdOn;
    private String content;

    public abstract void sendNotification(Person person);
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
