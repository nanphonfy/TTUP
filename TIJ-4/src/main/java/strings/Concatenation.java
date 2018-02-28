package strings;//: strings/Concatenation.java

/**
 * javap -c Concatenation 反编译
 * -c标志表示将生产JVM字节码
 */
public class Concatenation {
    public static void main(String[] args) {
        //编译器自动引入了StringBuilder类，用以构造最终的String，并为每个字符串调用一次StringBuilder的append方法，一共四次。
        //（编译器自动优化性能）
        String mango = "mango";
        String s = "abc" + mango + "def" + 47;
        System.out.println(s);
    }
}
/* Output:
abcmangodef47
*///:~
