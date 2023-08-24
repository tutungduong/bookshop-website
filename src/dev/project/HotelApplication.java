package dev.project;

import java.util.ArrayList;
import java.util.Arrays;

public class HotelApplication {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();

        int n = 5;
        for(int i = 0 ; i < n ; ++i){
            list.add(new Person());
            System.out.println(list.get(i));
            System.out.println("----------------");
        }
    }
}
