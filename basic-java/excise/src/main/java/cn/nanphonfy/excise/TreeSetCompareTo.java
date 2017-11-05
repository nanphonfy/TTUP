package cn.nanphonfy.excise;

import java.util.TreeSet;

/**
 * TreeSet里放对象，同时放入父类和子类的实例对象，比较时使用的是父类的compareTo方法，
 * 还是使用的子类的compareTo方法，还是抛异常！
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/5
 */
public class TreeSetCompareTo {
    /**
     * 当前的 add 方法放入的是哪个对象，就调用哪个对
     * 象的 compareTo 方法，至于这个 compareTo 方法怎么做，就看当前这个对象的类中是如何
     * 编写这个方法的
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeSet set = new TreeSet();
        set.add(new ParentTreeSetElement(3));
        set.add(new ChildTreeSetElement(5));
        set.add(new ParentTreeSetElement(5));
        System.out.println(set);
        System.out.println(set.size());

        //        method ofparent
        //        methodof child
        //        method ofparent
        //        method ofparent
        //        method ofparent
        //        [ParentTreeSetElement{age=3}, ChildTreeSetElement{age=5}]
        //        2
    }
}

class ParentTreeSetElement implements Comparable {
    private int age = 0;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ParentTreeSetElement(int age) {
        this.age = age;
    }

    @Override public int compareTo(Object o) {
        System.out.println("method ofparent");
        ParentTreeSetElement o1 = (ParentTreeSetElement) o;
        return age > o1.age ? 1 : age < o1.age ? -1 : 0;
    }

    @Override public String toString() {
        return "ParentTreeSetElement{" +
                "age=" + age +
                '}';
    }
}

class ChildTreeSetElement extends ParentTreeSetElement {
    public ChildTreeSetElement(int age) {
        super(age);
    }

    @Override public int compareTo(Object o) {
        System.out.println("methodof child");
        return super.compareTo(o);
    }

    @Override public String toString() {
        return "ChildTreeSetElement{age=" + super.getAge() + "}";
    }
}
