package designPattern;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class CorrectedSingleton implements Serializable {

    private static CorrectedSingleton soleSingleton = null;

    private CorrectedSingleton(){
        //Reflection
        if(soleSingleton != null){
            throw new RuntimeException("Cannot create instance use getInstance()");
        }
        soleSingleton = this;
        System.out.println("Creating...");
    }

    public static CorrectedSingleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new CorrectedSingleton();
        }
        return soleSingleton;
    }

    private Object readResolve() throws ObjectStreamException {
        System.out.println("...read resolve...");
        return soleSingleton;
    }
}

class TestSingleton{

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

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
       singletonBreakUsingReflection();
    }
}
