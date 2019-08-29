package hackerrank_java.advanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Ujjwal Gupta on Aug,2019
 */

/**
 * Java allows for Covariant Return Types, which means you can vary your return type as long you are returning a subclass of
 * your specified return type.
 *
 * Method Overriding allows a subclass to override the behavior of an existing superclass method and specify a return type
 * that is some subclass of the original return type.
 */
class Flower {
    public String whatsYourName(){
        return "I have many names and types";
    }
}

class Jasmine extends Flower{
    @Override
    public String whatsYourName(){
        return "Jasmine";
    }
}

class Lily extends Flower{
    @Override
    public String whatsYourName(){
        return "Lily";
    }
}

class Region {
    public Flower yourNationalFlower(){
        return new Flower();
    }
}

class WestBengal extends Region{
    @Override
    public Jasmine yourNationalFlower(){
        return new Jasmine();
    }
}



class AndhraPradesh extends Region{
    @Override
    public Lily yourNationalFlower() {
        return new Lily();
    }
}

public class CovariantReturnType {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine().trim();
        Region region = null;
        switch (s) {
            case "WestBengal":
                region = new WestBengal();
                break;
            case "AndhraPradesh":
                region = new AndhraPradesh();
                break;
        }
        Flower flower = region.yourNationalFlower();
        System.out.println(flower.whatsYourName());
    }
}
