package designPattern.singleton;

public enum EnumSingleton {

    INSTANCE;

    public String callSomeAPI(){
        return "SOME_API";
    }
}
