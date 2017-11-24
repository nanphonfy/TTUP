//: net/mindview/util/TwoTuple.java
package net.mindview.util;

public class TwoTuple<A, B> {
    /**
     * 是否违反java安全性原则
     * 无法赋予值，这种格式更加简洁
     */
    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    @Override public String toString() {
        return "(" + first + ", " + second + ")";
    }
} ///:~
