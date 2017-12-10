package generics;//: generics/SuperTypeWildcards.java

import java.util.List;

public class SuperTypeWildcards {
    static void writeTo(List<? super Apple> apples) {
        /**
         * 参数 Apple是 Apple的某种基类型的List，向其添加 Apple或 Apple的子类型是安全的
         */
        apples.add(new Apple());
        apples.add(new Jonathan());
        // apples.add(new Fruit()); // Error
    }
} ///:~
