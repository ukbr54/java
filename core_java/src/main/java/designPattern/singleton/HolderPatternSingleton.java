package designPattern.singleton;

/**
 * initialization on demand 
 */
public class HolderPatternSingleton {

    public static HolderPatternSingleton getInstance(){
         return Holder.INSTANCE;
    }

    static class Holder{
        static final HolderPatternSingleton INSTANCE = new HolderPatternSingleton();
    }
}
