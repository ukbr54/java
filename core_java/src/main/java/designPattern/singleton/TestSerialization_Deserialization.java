package designPattern.singleton;

import java.io.*;

public class TestSerialization_Deserialization {

    static <T> void print(String name, T object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Serialization_DeserializationSingleton s1 = Serialization_DeserializationSingleton.getInstance();
        Serialization_DeserializationSingleton s2 = Serialization_DeserializationSingleton.getInstance();
        print("s1",s1);
        print("s2",s2);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/s2.ser"));
        oos.writeObject(s2);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/s2.ser"));
        Serialization_DeserializationSingleton s3 = (Serialization_DeserializationSingleton)ois.readObject();

        print("s3",s3);
    }
}
