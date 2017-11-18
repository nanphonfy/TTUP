package exceptions;
//: exceptions/Rethrowing.java
// Demonstrating fillInStackTrace()

public class Rethrowing {
    public static void f() throws Exception {
        System.out.println("originating the exception in f()");
        throw new Exception("thrown from f()");
    }

    public static void g() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside g(),e.printStackTrace()");
            e.printStackTrace(System.out);
            //printStackTrace并非重新抛出异常对象
            throw e;
        }
    }

    public static void h() throws Exception {
        try {
            f();
        } catch (Exception e) {
            System.out.println("Inside h(),e.printStackTrace()");
            e.printStackTrace(System.out);
            //fillInStackTrace会更新信息，高一级捕获到该对象
            throw (Exception) e.fillInStackTrace();
        }
    }

    public static void main(String[] args) {
        /*originating the exception in f()
        Inside g(),e.printStackTrace()
        java.lang.Exception: thrown from f()
        at exceptions.Rethrowing.f(Rethrowing.java:8)
        at exceptions.Rethrowing.g(Rethrowing.java:13)
        at exceptions.Rethrowing.main(Rethrowing.java:33)
        main: printStackTrace()
        java.lang.Exception: thrown from f()
        at exceptions.Rethrowing.f(Rethrowing.java:8)
        at exceptions.Rethrowing.g(Rethrowing.java:13)
        at exceptions.Rethrowing.main(Rethrowing.java:33)*/
        try {
            g();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
        /*originating the exception in f()
        Inside h(),e.printStackTrace()
        java.lang.Exception: thrown from f()
        at exceptions.Rethrowing.f(Rethrowing.java:8)
        at exceptions.Rethrowing.h(Rethrowing.java:23)
        at exceptions.Rethrowing.main(Rethrowing.java:39)
        main: printStackTrace()
        java.lang.Exception: thrown from f()
        at exceptions.Rethrowing.h(Rethrowing.java:27)
        at exceptions.Rethrowing.main(Rethrowing.java:39)*/
        try {
            h();
        } catch (Exception e) {
            System.out.println("main: printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
} /* Output:
originating the exception in f()
Inside g(),e.printStackTrace()
java.lang.Exception: thrown from f()
        at Rethrowing.f(Rethrowing.java:7)
        at Rethrowing.g(Rethrowing.java:11)
        at Rethrowing.main(Rethrowing.java:29)
main: printStackTrace()
java.lang.Exception: thrown from f()
        at Rethrowing.f(Rethrowing.java:7)
        at Rethrowing.g(Rethrowing.java:11)
        at Rethrowing.main(Rethrowing.java:29)
originating the exception in f()
Inside h(),e.printStackTrace()
java.lang.Exception: thrown from f()
        at Rethrowing.f(Rethrowing.java:7)
        at Rethrowing.h(Rethrowing.java:20)
        at Rethrowing.main(Rethrowing.java:35)
main: printStackTrace()
java.lang.Exception: thrown from f()
        at Rethrowing.h(Rethrowing.java:24)
        at Rethrowing.main(Rethrowing.java:35)
*///:~
