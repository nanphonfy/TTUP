package cn.nanphonfy.excise;

import java.util.Date;

/**
 * Created by nanphonfy(南风zsr) on 2017/11/2.
 */
public class SuperGetClass extends Date {
    public static void main(String[] args) {
        SuperGetClass superGetClass = new SuperGetClass();
        //cn.nanphonfy.excise.SuperGetClass
        superGetClass.test0();
        //java.util.Date
        superGetClass.test1();
    }

    /**
     * 由于getClass()在 Object 类中定义成了 final，子类不能覆盖该方法
     * 所以，在test0方法中调用getClass().getName()方法， 其实就是在调用从父类继承的getClass()方法
     * 等效于调用 super.getClass().getName()方法，所以，super.getClass().getName()方法返回的是SuperGetClass
     */
    public void test0() {
        //Object//public final native Class<?> getClass()
        System.out.println(getClass().getName());
        System.out.println(super.getClass().getName());
    }

    public void test1() {
        System.out.println(getClass().getSuperclass().getName());
    }
}
