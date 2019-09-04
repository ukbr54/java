package designPattern.singleton;

public class TestCloneable {

    static <T> void print(String name, T object){
        System.out.println(String.format("Object:  %s, Hashcode:  %d", name,object.hashCode()));
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        CloneableSingleton s1 = CloneableSingleton.getInstance();
        CloneableSingleton s2 = CloneableSingleton.getInstance();
        print("s1",s1);
        print("s2",s2);

        CloneableSingleton s3 = (CloneableSingleton)s2.clone();
        print("s3",s3);
    }
}
