package typeinfo;
//: typeinfo/InterfaceViolation.java
// Sneaking around an interface.

import typeinfo.interfacea.A;

class B implements A {
    @Override
    public void f() {
    }

    public void g() {
    }
}

public class InterfaceViolation {
    public static void main(String[] args) {
        A a = new B();
        a.f();
        // a.g(); // Compile error
        /**
         * 通过RTTI，a被当做B实现，通过将其转型为B，可调用不在A中的方法
         */
        //B
        System.out.println(a.getClass().getName());
        if (a instanceof B) {
            B b = (B) a;
            b.g();
        }
    }
}
/* Output:
B
*///:~
