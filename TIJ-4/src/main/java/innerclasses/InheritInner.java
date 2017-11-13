package innerclasses;
//: innerclasses/InheritInner.java
// Inheriting an inner class.

class WithInner {
    class Inner {
    }
}

public class InheritInner extends WithInner.Inner {
    //! InheritInner() {} // Won't compile

    /**
     * 内部类的构造器必须连接到指向其外围类对象的引用，所以在继承内部类时，那个指向外围类对象的"秘密"的引用必须被初始化
     *
     * @param wi
     */
    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
} ///:~
