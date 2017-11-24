package generics;
//: generics/Holder2.java

public class Holder2 {
    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new Automobile());
        Automobile a = (Automobile) h2.get();
        //generics.Automobile@135fbaa4
        System.out.println(a);

        h2.set("Not an Automobile");
        String s = (String) h2.get();
        //Not an Automobile
        System.out.println(s);

        h2.set(1); // Autoboxes to Integer
        Integer x = (Integer) h2.get();
        //1
        System.out.println(x);
    }
} ///:~
