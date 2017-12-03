package generics;
//: generics/Manipulation.java
// {CompileTimeError} (Won't compile)

class Manipulator<T> {
    private T obj;

    public Manipulator(T x) {
        obj = x;
    }

    /**
     * 因为擦除，java编译器无法映射HasF对象拥有f()
     */
    // Error: cannot find symbol: method f():
    public void manipulate() {
        //obj.f();
    }
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();
    }
} ///:~
