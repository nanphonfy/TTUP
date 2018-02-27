package typeinfo;//: typeinfo/InnerImplementation.java
// Private inner classes can't hide from reflection.

import typeinfo.interfacea.A;

import static net.mindview.util.Print.print;

class InnerA {
    private static class C implements A {
        @Override public void f() {
            print("public C.f()");
        }

        public void g() {
            print("public C.g()");
        }

        void u() {
            print("package C.u()");
        }

        protected void v() {
            print("protected C.v()");
        }

        private void w() {
            print("private C.w()");
        }
    }

    public static A makeA() {
        return new C();
    }
}

/**
 * 若将接口实现为一个私有内部类，对反射仍旧无任何隐藏
 */
public class InnerImplementation {
    public static void main(String[] args) throws Exception {
        A a = InnerA.makeA();
        a.f();
        //typeinfo.InnerA$C
        System.out.println(a.getClass().getName());
        // Reflection still gets into the private class:
        HiddenImplementation.callHiddenMethod(a, "g");
        HiddenImplementation.callHiddenMethod(a, "u");
        HiddenImplementation.callHiddenMethod(a, "v");
        HiddenImplementation.callHiddenMethod(a, "w");
    }
}
/* Output:
public C.f()
InnerA$C
public C.g()
package C.u()
protected C.v()
private C.w()
*///:~
