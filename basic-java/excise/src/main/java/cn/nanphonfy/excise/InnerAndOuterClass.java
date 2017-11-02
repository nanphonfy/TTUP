package cn.nanphonfy.excise;

/**
 * 什么是内部类？Static Nested Class和Inner Class的不同
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/10/29
 */
public class InnerAndOuterClass {
    //内部类:在一个类的内部定义的类，内部类中不能定义静态成员
    //内部类可以直接访问外部类中的成员变量
}

class Outer {
    public int outX = 0;
    public static int staticOutX = 0;

    public void method() {
        Inner1 inner1 = new Inner1();
        /**
         * 在方法体内部定义的内部类
         * 不能有访问类型修饰符(相当于局部变量),但可以使用final或abstract修饰符
         *
         */
        class Inner2 {
            public void method() {
                outX = 3;
            }
        }
        Inner2 inner2 = new Inner2();
    }

    /**
     * 在方法体外面定义的内部类
     * 可以为public,protecte,默认，private等4种类型
     */
    private class Inner1 {
        /**
         * 内部类可以引用它的包含类的成员，静态嵌套类则不可以
         */
        public void take() {
            outX = 2;
        }
    }

    /**
     * 静态内部类
     * 不再具有内部类的特性,狭义上讲，它不是内部类
     * 可以为public,protecte,默认，private等4种类型，普通类只能定义成 public和默认
     */
    private static class Inner2 {
        public void take() {
            //不能直接访问外部类的非static成员变量
            //outX//Non-static field 'outX' cannot be referenced from a static context

            //需创建外部类实例才能访问非静态变量
            int i = new Outer().outX;

            //可直接访问外部类的静态变量
            staticOutX = 2;
        }
    }

    /**
     * 静态方法中定义的内部类也是静态内部类
     */
    public static void staticMethod() {
        final boolean flag = true;
        /**
         * 这时不能在类前面加static
         * 可直接访问外部类的static成员变量，还可访问静态方法中的局部变量，但是，该局部变量前必须加 final 修饰符
         */
        class Inner {
            public boolean method() {
                return flag;
            }
        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner1 inner1 = outer.new Inner1();

        //静态内部类不依赖于外部类的实例对象
        Outer.Inner2 inner2 = new Outer.Inner2();
    }
}

/**
 * 匿名内部类【必须继承其他类或实现其他接口】
 * 即定义某一接口或类的子类的同时，还创建了该子类的实例对象，无需为该子类定义名称
 * 只要一个类是抽象的或是一个接口，那么其子类中的方法都可以使用匿名内部类来实现
 * 最常用的情况就是在多线程的实现上，因为要实现多线程必须继承Thread类或是继承Runnable接口
 */
class Outer2 {
    public void start() {
        new Thread(new Runnable() {
            @Override public void run() {
            }
        }).start();
    }
}
