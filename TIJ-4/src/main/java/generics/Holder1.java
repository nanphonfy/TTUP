package generics;
//: generics/Holder1.java

class Automobile {
}

/**
 * 只能持有单个对象（明确指定持有对象的类型）的类
 */
public class Holder1 {
    private Automobile a;

    public Holder1(Automobile a) {
        this.a = a;
    }

    Automobile get() {
        return a;
    }
} ///:~
