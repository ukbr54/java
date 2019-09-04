package designPattern;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CorrectedSingleton implements Serializable,Cloneable {

    private static CorrectedSingleton soleSingleton = null;

    private CorrectedSingleton(){
        //Reflection
        if(soleSingleton != null){
            throw new RuntimeException("Cannot create instance use getInstance()");
        }
        soleSingleton = this;
        System.out.println("Creating...");
    }

    public static synchronized CorrectedSingleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new CorrectedSingleton();
        }
        return soleSingleton;
    }

    //Serialization/Deserialization
    private Object readResolve() throws ObjectStreamException {
        System.out.println("...read resolve...");
        return soleSingleton;
    }

    //Clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone not supported!");
    }
}

class TestSingleton{

    //multi-threaded
    static void useSingleton(){
        CorrectedSingleton s1 = CorrectedSingleton.getInstance();
        print("Singleton",s1);
    }

    private static void singletonBreakUsingMultiThreaded(){
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestClass::useSingleton);
        service.submit(TestClass::useSingleton);
        service.shutdown();
    }

    static void print(String name,CorrectedSingleton object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    private static void singletonBreakUsingReflection() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        //Reflection
        final Class<?> clazz = Class.forName("designPattern.CorrectedSingleton");
        final Constructor<CorrectedSingleton> constructor = (Constructor<CorrectedSingleton>) clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        CorrectedSingleton s3 = constructor.newInstance();
        print("Reflection",s3);

        CorrectedSingleton s1 = CorrectedSingleton.getInstance();
        CorrectedSingleton s2 = CorrectedSingleton.getInstance();

        print("s1",s1);
        print("s2",s2);

    }

    private static void singletonBreakUsingSerialization() throws IOException, ClassNotFoundException {
        CorrectedSingleton s2 = CorrectedSingleton.getInstance();
        //Serialization
        final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("/tmp/s2.ser"));
        oos.writeObject(s2);

        final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/tmp/s2.ser"));
        final CorrectedSingleton s4 = (CorrectedSingleton)ois.readObject();
        print("Serialization/Deserialization",s4);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
       //singletonBreakUsingReflection();
        //singletonBreakUsingSerialization();
        singletonBreakUsingMultiThreaded();
    }
}
