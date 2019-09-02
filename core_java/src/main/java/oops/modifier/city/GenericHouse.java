package oops.modifier.city;

import oops.modifier.street21.House;

public class GenericHouse extends House {

    public static void main(String[] args) {
        GenericHouse h = new GenericHouse();
        h.number = 100; //this attribute is protected and GenericHouse can access it because it extends House.
        //h.address = "";//protected access,
        //h.reference = ""; //compile-time-error, private access
        //h.printNumber(); //compile-time-error, default package access
        h.printInformation(); //public access
        //Bluehouse bh = new Bluehouse(); // Compile-time error, default package access
        //bh.getColor(); //compile-time-error
    }
}
