package innerclasses;
//: innerclasses/Parcel9.java
// An anonymous inner class that performs
// initialization. A briefer version of Parcel5.java.

public class Parcel9 {
    // Argument must be final to use inside
    // anonymous inner class:

    /**
     * 若定义一个匿名内部类，并希望使用外部定义的对象
     * 编译器会要求参数引用为final
     *
     * @param dest
     * @return
     */
    public Destination destination(final String dest) {
        return new Destination() {
            private String label = dest;

            @Override public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
    }
} ///:~
