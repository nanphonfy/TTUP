package strings;//: strings/InfiniteRecursion.java
// Accidental recursion.
// {RunByHand}

import java.util.ArrayList;
import java.util.List;

public class InfiniteRecursion {
    /**
     * 编译器看到一个String对象后跟着一个"+",而后面的对象不是String，
     * 于是编译器试图将this转换成一个String（调用this上的toString，递归调用）
     * 若真想打印出对象的内存地址，应该调用Object.toString，不该使用this，而使用super.toString。
     */
    @Override
    public String toString() {
        //return " InfiniteRecursion address: " + this + "\n";
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            v.add(new InfiniteRecursion());
        }
        System.out.println(v);
    }
} ///:~
