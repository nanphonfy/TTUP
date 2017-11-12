package innerclasses;
//: innerclasses/AnonymousConstructor.java
// Creating a constructor for an anonymous inner class.

import static net.mindview.util.Print.print;

abstract class Base {
    public Base(int i) {
        print("Base constructor, i = " + i);
    }

    public abstract void f();
}

public class AnonymousConstructor {
    /**
     * 不要求变量i一定是final，因为i不会直接被匿名类使用
     * @param i
     * @return
     */
    public static Base getBase(int i) {
        return new Base(i) {
            {
                print("Inside instance initializer");
            }

            @Override public void f() {
                print("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
} /* Output:
Base constructor, i = 47
Inside instance initializer
In anonymous f()
*///:~
