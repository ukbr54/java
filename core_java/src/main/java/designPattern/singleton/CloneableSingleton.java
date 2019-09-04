package designPattern.singleton;

public class CloneableSingleton implements Cloneable{

    private static CloneableSingleton soleSingleton = null;

    private CloneableSingleton(){
        System.out.println("Creating.....");
    }

    public static CloneableSingleton getInstance(){
        if(soleSingleton == null){
            soleSingleton = new CloneableSingleton();
        }
        return soleSingleton;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    //cloneable

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clone is not supported");
    }
}
