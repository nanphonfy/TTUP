package innerclasses;//: innerclasses/Parcel8.java
// Calling the base-class constructor.

public class Parcel8 {
    public Wrapping wrapping(int x) {
        // Base constructor call:
        /**
         * 尽管Wrapping只是一个有具体实现的普通类，但它还是被当做公共"接口"（基类）使用
         */
        return new Wrapping(x) { // Pass constructor argument.
            @Override public int value() {
                return super.value() * 47;
            }
        }; // Semicolon required
        //匿名内部类末尾的分号，标记的是表达式的结束
    }

    public static void main(String[] args) {
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }
} ///:~

class Wrapping {
    private int i;

    public Wrapping(int x) {
        i = x;
    }

    public int value() {
        return i;
    }
} ///:~
