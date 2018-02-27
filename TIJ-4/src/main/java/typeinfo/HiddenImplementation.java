package typeinfo;//: typeinfo/HiddenImplementation.java
// Sneaking around package access.

import typeinfo.interfacea.A;
import typeinfo.packageaccess.HiddenC;
import java.lang.reflect.Method;

public class HiddenImplementation {
    /**
     * 唯一的public类HiddenC可在其他包调用，产生A接口类型的对象（即使返回的是C类型）。
     * 包外不能命名C，若试图向下转型C，将被禁止。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        //typeinfo.packageaccess.C
        System.out.println(a.getClass().getName());
        // Compile error: cannot find symbol 'C':
        //在包外，不能调用C类
         /*if(a instanceof C) {
          C c = (C)a;
          c.g();
        } */
        // Oops! Reflection still allows us to call g():
        callHiddenMethod(a, "g");
        // And even methods that are less accessible!
        callHiddenMethod(a, "u");
        callHiddenMethod(a, "v");
        callHiddenMethod(a, "w");
    }

    /**
     * 而通过反射，仍旧可以调用所有方法，甚至private。若知道方法名，就可在其对象Method对象调用setAccessible(true)，就像在callHiddenMethod中一样。
     * 只发布编译后的代码，也并不解决问题，因为只需运行javap（反编译器）即可突破限制。
     * javap -private C
     * 因此任何人都可获取最私有的方法的名字和签名，并调用。
     *
     * @param a
     * @param methodName
     * @throws Exception
     */
    static void callHiddenMethod(Object a, String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);
        g.setAccessible(true);
        g.invoke(a);
    }
}
/* Output:
public C.f()
typeinfo.packageaccess.C
public C.g()
package C.u()
protected C.v()
private C.w()
*///:~
