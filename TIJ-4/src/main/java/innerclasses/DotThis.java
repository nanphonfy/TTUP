package innerclasses;
//: innerclasses/DotThis.java
// Qualifying access to the outer-class object.

public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        /**
         * 内部类需生成对外部类对象的引用
         * @return
         */
        public DotThis outer() {
            //若在内部类需生成对外部类对象的引用，可使用 外部类.this
            return DotThis.this;
            // A plain "this" would be Inner's "this"
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
} /* Output:
DotThis.f()
*///:~
