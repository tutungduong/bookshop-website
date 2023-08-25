package dev.project;

import java.util.Scanner;

public class Account {
    private String id;
    private String password;
    private Enums.AccountStatus status;

    private static int ID = 1000;

    private int defaultpasswordLength = 10;

    public Account(String id) {
        // Username
        this.id = id ;
        this.password = randomPassword(defaultpasswordLength);
        this.status = status.ACTIVE;
//        System.out.println("Username: " + id);
//        System.out.println("Your current password is: " + this.password);
//        System.out.println(resetPassword() ? "You have successfully changed your password" : "The current password is the default password");
    }
    private String getID(){
        ID++;
        return new String(String.valueOf(ID));
    }

    public String getId() {
        return id;
    }

    // Generate a random password
    private String randomPassword(int length){
        String passwordSet="ABCDEFGHIJKLMNOPQRSTUWXYZ0123456789!@#$%";
        char [] password = new char[length];
        for(int i = 0 ; i < length ; ++i){
            int rand = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(rand);
        }
        return new String(password);
    }

    public boolean resetPassword(){
        Scanner in = new Scanner(System.in);
        System.out.print("Do you want to change your password? (Y/N): ");
        String ch = in.nextLine().toUpperCase();
        switch (ch){
            case "Y" -> {
                System.out.print("Please enter a new password: ");
                this.password = in.nextLine();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
