package innerclasses;
//: innerclasses/Parcel7.java
// Returning an instance of an anonymous inner class.

public class Parcel7 {
    public Contents contents() {
        /*返回值的生成与表示这个返回值的类的定义结合在一起，它是匿名的，没名字
        这种语法指：创建一个继承或实现Contents的匿名类的对象*/
        // Insert a class definition
        return new Contents() {
            private int i = 11;

            @Override public int value() {
                return i;
            }
        }; // Semicolon required in this case
    }

    public static void main(String[] args) {
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }
} ///:~
