package designPattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  Ways to violate Singleton class instance:
 *  1. Reflection
 *  2. Serialization/Deserialization
 *  3. Clone
 *  4. multi-threaded access
 *  5. multiple class loaders
 *  6. Garbage Collection
 */
public class Singleton implements Serializable,Cloneable {

    //private static Singleton soleSingleton = new Singleton(); //eager
    private static Singleton soleSingleton = null;

    private Singleton(){
        System.out.println("Creating....");
    }

    public static Singleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new Singleton();
        }
        return soleSingleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class TestClass{

    private static void checkSingleton(){
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        print("s1",s1);
        print("s2",s2);
    }

    private static void singletonBreakUsingReflection() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        //Reflection
        final Class<?> clazz = Class.forName("designPattern.Singleton");
        final Constructor<Singleton> constructor = (Constructor<Singleton>) clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Singleton s3 = constructor.newInstance();
        print("Reflection",s3);
    }

    private static void singletonBreakUsingSerialization() throws IOException, ClassNotFoundException {
        Singleton s2 = Singleton.getInstance();
        //Serialization
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/s2.ser"));
        oos.writeObject(s2);

        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/s2.ser"));
        final Singleton s4 = (Singleton)ois.readObject();
        print("Serialization/Deserialization",s4);
    }

    private static void singletonBreakUsingClone() throws CloneNotSupportedException {
        //clone
        Singleton s2 = Singleton.getInstance();
        final Singleton s5 = (Singleton)s2.clone();
        print("Clone",s5);
    }

    //multi-threaded
    static void useSingleton(){
        Singleton s1 = Singleton.getInstance();
        print("Singleton",s1);
    }

    private static void singletonBreakUsingMultiThreaded(){
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestClass::useSingleton);
        service.submit(TestClass::useSingleton);
        service.shutdown();
    }



    static void print(String name,Singleton object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException,
            IOException, CloneNotSupportedException {

        singletonBreakUsingMultiThreaded();
    }
}
