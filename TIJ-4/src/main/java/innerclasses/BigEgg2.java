package innerclasses;
//: innerclasses/BigEgg2.java
// Proper inheritance of an inner class.

import static net.mindview.util.Print.print;

class Egg2 {
    protected class Yolk {
        public Yolk() {
            print("Egg2.Yolk()");
        }

        public void f() {
            print("Egg2.Yolk.f()");
        }
    }

    private Yolk y = new Yolk();

    public Egg2() {
        print("New Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    public void g() {
        y.f();
    }
}

public class BigEgg2 extends Egg2 {
    public class Yolk extends Egg2.Yolk {
        public Yolk() {
            print("BigEgg2.Yolk()");
        }

        @Override public void f() {
            print("BigEgg2.Yolk.f()");
        }
    }

    public BigEgg2() {
        /**
         * 方insertYolk法允许BigEgg2将自己的Yolk对象向上转型为Egg2中的引用y
         * 第二次调用Egg2.Yolk，结果是BigEgg2的构造器调用了其基类的构造器
         */
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        /*Egg2.Yolk()
        New Egg2()
        Egg2.Yolk()
        BigEgg2.Yolk()*/
        Egg2 e2 = new BigEgg2();
        //BigEgg2.Yolk.f()
        e2.g();
    }
} /* Output:
Egg2.Yolk()
New Egg2()
Egg2.Yolk()
BigEgg2.Yolk()
BigEgg2.Yolk.f()
*///:~
