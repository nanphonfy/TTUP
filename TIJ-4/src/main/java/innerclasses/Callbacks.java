//: innerclasses/Callbacks.java
// Using inner classes for callbacks
package innerclasses;

import static net.mindview.util.Print.print;

interface Incrementable {
    void increment();
}

// Very simple to just implement the interface:
/**
 * 外部类实现接口
 */
class Callee1 implements Incrementable {
    private int i = 0;

    @Override public void increment() {
        i++;
        print(i);
    }
}

class MyIncrement {
    /**
     * 不同于Incrementable接口的increment方法
     */
    public void increment() {
        print("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}

// If your class must implement increment() in
// some other way, you must use an inner class:
class Callee2 extends MyIncrement {
    private int i = 0;

    @Override public void increment() {
        super.increment();
        i++;
        print(i);
    }

    /**
     * 内部类实现接口
     */
    private class Closure implements Incrementable {
        @Override public void increment() {
            // Specify outer-class method, otherwise
            // you'd get an infinite recursion:
            Callee2.this.increment();
        }
    }

    /**
     * 其他成员都是private。该方法：interface如何允许接口与及接口的实现完全独立
     * 无论谁获得Incrementable接口，都只能调用increment方法
     * 除此之外，没有其他功能（指针可以允许做很多事）
     * @return
     */
    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
//        Callee1 c1 = new Callee1();
//        Callee2 c2 = new Callee2();
//        //Other operation
//        //1
//        MyIncrement.f(c2);
//
//        Caller caller1 = new Caller(c1);
//        Caller caller2 = new Caller(c2.getCallbackReference());
//        //1
//        //2
//        caller1.go();
//        caller1.go();
//        /*Other operation
//        2
//        Other operation
//        3*/
//        caller2.go();
//        caller2.go();
    }
} /* Output:
Other operation
1
1
2
Other operation
2
Other operation
3
*///:~
