package designPattern.singleton;


public class ReflectionSingleton {

    private static ReflectionSingleton soleSingleton = null;

    private ReflectionSingleton(){
        /**
         * Reflection : To handle reflection we will throw a exception if the instance is already created from new keyword
         * and later created the instance using Reflection.
         *
         * If the first instance is created from the reflection then simply assign to this variable so that it will
         * not break for the later new keyword instance creation.
         */

        if(soleSingleton != null){
            throw new RuntimeException("Cannot create instance use getInstance()");
        }
        soleSingleton = this;
        System.out.println("Creating...");
    }

    public static ReflectionSingleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new ReflectionSingleton();
        }
        return soleSingleton;
    }
}
