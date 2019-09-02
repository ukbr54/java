package oops.modifier.street21;

public class House {

    protected int number;
    private String reference;

    void printNumber(){
        System.out.println("Num: "+number);
    }

    public void printInformation(){
        System.out.println("Num: " + number);
        System.out.println("Ref: " + reference);
    }
}
