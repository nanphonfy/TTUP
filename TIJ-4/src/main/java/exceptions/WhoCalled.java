package exceptions;
//: exceptions/WhoCalled.java
// Programmatic access to stack trace information.

public class WhoCalled {
    static void f() {
        // Generate an exception to fill in the stack trace
        try {
            throw new Exception();
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace()) {
                //只打印了方法名，还可打印整个StackTraceElement
                System.out.println(ste.getMethodName() + "(" + ste + ")");
            }
        }
    }

    static void g() {
        f();
    }

    static void h() {
        g();
    }

    public static void main(String[] args) {
        f();
        System.out.println("--------------------------------");
        g();
        System.out.println("--------------------------------");
        h();
    }
}
/*f(exceptions.WhoCalled.f(WhoCalled.java:9))
main(exceptions.WhoCalled.main(WhoCalled.java:27))
invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
invoke(java.lang.reflect.Method.invoke(Method.java:497))
main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))
--------------------------------
f(exceptions.WhoCalled.f(WhoCalled.java:9))
g(exceptions.WhoCalled.g(WhoCalled.java:19))
main(exceptions.WhoCalled.main(WhoCalled.java:29))
invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
invoke(java.lang.reflect.Method.invoke(Method.java:497))
main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))
--------------------------------
f(exceptions.WhoCalled.f(WhoCalled.java:9))
g(exceptions.WhoCalled.g(WhoCalled.java:19))
h(exceptions.WhoCalled.h(WhoCalled.java:23))
main(exceptions.WhoCalled.main(WhoCalled.java:31))
invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
invoke(java.lang.reflect.Method.invoke(Method.java:497))
main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))*/
