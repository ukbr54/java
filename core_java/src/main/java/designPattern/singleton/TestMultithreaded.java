package designPattern.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMultithreaded {

    static <T> void print(String name, T object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    public static void useSingletonUsingDoubleLocking(){
        MultithreadSingleton s1 = MultithreadSingleton.getInstance();
        System.out.println("Thread: "+Thread.currentThread().getName());
        print("s1",s1);
    }

    public static void useSingletonUsingHolder(){
        HolderPatternSingleton h1 = HolderPatternSingleton.getInstance();
        print("h1 ------>",h1);
    }

    public static void useSingletonUsingEnum(){
        final EnumSingleton instance = EnumSingleton.INSTANCE;
        print("enum",instance);
    }


    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(TestMultithreaded::useSingletonUsingDoubleLocking);
        service.submit(TestMultithreaded::useSingletonUsingDoubleLocking);
        service.shutdown();


        ExecutorService service1 = Executors.newFixedThreadPool(2);
        service1.submit(TestMultithreaded::useSingletonUsingHolder);
        service1.submit(TestMultithreaded::useSingletonUsingHolder);
        service1.shutdown();

        ExecutorService service2 = Executors.newFixedThreadPool(2);
        service2.submit(TestMultithreaded::useSingletonUsingEnum);
        service2.submit(TestMultithreaded::useSingletonUsingEnum);
        service2.shutdown();
    }
}
