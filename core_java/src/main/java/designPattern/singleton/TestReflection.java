package designPattern.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TestReflection {

    static <T> void print(String name, T object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

//        ReflectionSingleton s1 = ReflectionSingleton.getInstance();
//        ReflectionSingleton s2 = ReflectionSingleton.getInstance();
//        print("s1",s1);
//        print("s2",s2);

        final Class<?> clazz = Class.forName("designPattern.singleton.ReflectionSingleton");
        final Constructor<?> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        ReflectionSingleton s3 = (ReflectionSingleton) constructor.newInstance();
        print("s3",s3);

        ReflectionSingleton s1 = ReflectionSingleton.getInstance();
        ReflectionSingleton s2 = ReflectionSingleton.getInstance();
        print("s1",s1);
        print("s2",s2);
    }
}
