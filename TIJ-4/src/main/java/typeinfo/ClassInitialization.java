package typeinfo;
//: typeinfo/ClassInitialization.java

import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {
    static int staticNonFinal = 147;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int staticNonFinal = 74;

    static {
        System.out.println("Initializing Initable3");
    }
}

public class ClassInitialization {
    public static Random rand = new Random(47);

    /**
     * 使用.class对象引用时，不会自动地初始化该Class对象
     * ①加载（类加载器将查找字节码并创建一个Class对象）
     * ②链接（验证类中的字节码，为静态域分配存储空间，解析该类创建的对其他类的所有引用）
     * ③初始化（对其初始化，执行静态初始化器和静态初始化块）
     * 初始化被延迟到对静态方法（构造器隐式是静态的）或非数静态域进行首次引用时才执行
     */
    public static void main(String[] args) throws Exception {
        Class initable = Initable.class;
        System.out.println("After creating Initable ref");
        // Does not trigger initialization:
        System.out.println(Initable.staticFinal);

        /*static final【值】是编译器常量，值不需对Initable类初始化就可读取
        （将【域】设置为static和final，不确保这种行为）*/
//        Initializing Initable
        // Does trigger initialization:
        System.out.println(Initable.staticFinal2);

        // 若一个是static域，而不是final的，
        // 那么对它访问时，要先进行链接（为这个域分配存储空间）和初始化（初始化该存储空间）
        //Initializing Initable2
        // Does trigger initialization:
        System.out.println(Initable2.staticNonFinal);

        /*初始化有效实现了尽可能的惰性
        仅适用.class获得类引用，不会引发初始化
        Class.forName立即进行了初始化*/
        Class initable3 = Class.forName("typeinfo.Initable3");
        System.out.println("After creating Initable3 ref");
        System.out.println(Initable3.staticNonFinal);
    }
}
/* Output:
After creating Initable ref
47
Initializing Initable
258
Initializing Initable2
147
Initializing Initable3
After creating Initable3 ref
74
*///:~
