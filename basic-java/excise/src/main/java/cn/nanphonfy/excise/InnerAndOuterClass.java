package cn.nanphonfy.excise;

/**
 * 什么是内部类？Static Nested Class和Inner Class的不同
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/10/29
 */
public class InnerAndOuterClass {
    //内部类:在一个类的内部定义的类，内部类中不能定义静态成员
}

class Outer {
    int out_x = 0;

    public void method() {
        Inner1 inner1 = new Inner1();
        /**
         * 在方法体内部定义的内部类
         * 不能有访问类型修饰符(相当于局部变量),但可以使用final或abstract修饰符
         *
         */
        class Inner2 {
            public void method() {
                out_x = 3;
            }
        }
        Inner2 inner2 = new Inner2();
    }

    /**
     * 在方法体外面定义的内部类
     * 可以为public,protecte,默认，private等4种类型
     */
    public class Inner1 {
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner1 inner1 = outer.new Inner1();
    }
}

/**
 * 匿名内部类
 * 即定义某一接口或类的子类的同时，还创建了该子类的实例对象，无需为该子类定义名称
 */

