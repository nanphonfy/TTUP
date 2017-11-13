package innerclasses;
//: innerclasses/BigEgg.java
// An inner class cannot be overriden like a method.

import static net.mindview.util.Print.print;

class Egg {
    private Yolk y;

    protected class Yolk {
        public Yolk() {
            print("Egg.Yolk()");
        }
    }

    /**
     * 默认的构造器是编译器生成的，这里调用了基类的构造函数。
     */
    public Egg() {
        print("New Egg()");
        y = new Yolk();
    }
}

public class BigEgg extends Egg {
    public class Yolk {
        public Yolk() {
            print("BigEgg.Yolk()");
        }
    }

    public static void main(String[] args) {
        //该例子说明，当继承了某外围类，内部类没有发生变化，因为他们是完全独立的两个实体，各自在自己的命名空间内
        new BigEgg();
    }
} /* Output:
New Egg()
Egg.Yolk()
*///:~
