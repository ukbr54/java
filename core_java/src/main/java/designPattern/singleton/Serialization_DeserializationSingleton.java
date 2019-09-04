package designPattern.singleton;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Serialization_DeserializationSingleton implements Serializable {

    private static Serialization_DeserializationSingleton soleSingleton = null;

    private Serialization_DeserializationSingleton(){
        System.out.println("Creating.....");
    }

    public static Serialization_DeserializationSingleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new Serialization_DeserializationSingleton();
        }
        return soleSingleton;
    }

    //To handle Serialization
    private Object readResolve() throws ObjectStreamException {
        System.out.println("...read resolve...");
        return soleSingleton;
    }
}
