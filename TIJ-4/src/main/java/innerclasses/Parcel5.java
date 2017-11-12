package innerclasses;
//: innerclasses/Parcel5.java
// Nesting a class within a method.

public class Parcel5 {
    /**
     * 定义在作用域内的类，此作用域在方法内部
     * 在方法的作用域内——局部内部类
     * 方法执行完毕，PDestination就不可用
     */
    public Destination destination(String s) {
        class PDestination implements Destination {
            private String label;

            private PDestination(String whereTo) {
                label = whereTo;
            }

            @Override public String readLabel() {
                return label;
            }
        }
        //向上转型
        return new PDestination(s);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
    }
} ///:~
