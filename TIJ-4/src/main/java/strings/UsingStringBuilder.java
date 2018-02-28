package strings;//: strings/UsingStringBuilder.java

import java.util.Random;

public class UsingStringBuilder {
    public static Random rand = new Random(47);

    /**
     * 最终结果是用append一点点拼接起来的，若想走捷径（eg.append(a+":"+c)，编译器则会调入陷阱，另外创建一个StringBuilder）。
     * StringBuilder是SE5引入，在这之前java用的是StringBuffer（线程安全，开销较大）。
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
            result.append(rand.nextInt(100));
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("]");
        return result.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usb = new UsingStringBuilder();
        System.out.println(usb);
    }
} /* Output:
[58, 55, 93, 61, 61, 29, 68, 0, 22, 7, 88, 28, 51, 89, 9, 78, 98, 61, 20, 58, 16, 40, 11, 22, 4]
*///:~
