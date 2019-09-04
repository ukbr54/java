package designPattern.singleton;

/**
 * Double checking design pattern
 *
 * This utilizes the "double-checked locking" design pattern, and guarentees the following for the singleton:
 *
 *  1. Lazy initialization (great for resource allocation, and means we don't waste resources with eager initialization)
 *  2. No race condition for lazy initialization (two+ more threads each trying to create a singleton will fail,
 *     guarenteed by syncronized)
 *  3. Acquiring a lock (expensive operation) is done once and only once, thus there is no performance penalty
 *  4. Only allows the singleton to be used when it is fully instantiated (thanks to the volatile keyword there)
 */
public class MultithreadSingleton {

    private static volatile MultithreadSingleton soleSingleton = null;

    private MultithreadSingleton(){
        System.out.println("Creating.....");
    }

    public static MultithreadSingleton getInstance(){
        if(soleSingleton == null){
            synchronized (MultithreadSingleton.class){
                if(soleSingleton == null) soleSingleton = new MultithreadSingleton();
            }
        }
        return soleSingleton;
    }
}
