package generics;//: generics/BasicGeneratorDemo.java

import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

/**
 * 必须为public，否则不同包会出现错误
 */
/*class CountedObject {
    private static long counter = 0;
    private final long id = counter++;

    public long id() {
        return id;
    }

    @Override public String toString() {
        return "CountedObject " + id;
    }
} ///:~*/

public class BasicGeneratorDemo {
    public static void main(String[] args) {
        Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }
} /* Output:
CountedObject 0
CountedObject 1
CountedObject 2
CountedObject 3
CountedObject 4
*///:~
