package strings;//: strings/Immutable.java

import static net.mindview.util.Print.print;

public class Immutable {
    /**
     * 每当把String对象做为方法参数时，都会复制一份引用。
     * 而引用本身的对象，一直待在单一物理位置上。
     *
     * @param s
     * @return
     */
    public static String upcase(String s) {
        //传入的引用s只有upcase运行时才存在，结束则消失
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        print(q); // howdy
        //把q传给upcase方法时，实际传递了引用的拷贝。
        String qq = upcase(q);
        print(qq); // HOWDY
        print(q); // howdy
    }
}
/* Output:
howdy
HOWDY
howdy
*///:~
