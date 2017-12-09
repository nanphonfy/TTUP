package generics;
//: generics/GenericsAndCovariance.java

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {
    public static void main(String[] args) {
        /*class Fruit {
        }
        class Apple extends generics.Fruit {
        }
        class Jonathan extends generics.Apple {
        }
        class Orange extends generics.Fruit {
        }*/
        // Wildcards allow covariance:

        /**
         *  List<? extends Fruit>可以合法的指向一个ArrayList<Apple>
         一旦执行这种类型的向上转型，将丢失向其中传递任何对象的能力，甚至object也不行
         */
        List<? extends Fruit> flist = new ArrayList<Apple>();
        // Compile Error: can't add any type of object:
        //         flist.add(new Apple());
        //         flist.add(new Fruit());
        //         flist.add(new Object());
        //         flist.add(new Orange());
        flist.add(null); // Legal but uninteresting
        // We know that it returns at least Fruit:
        Fruit f = flist.get(0);
    }
} ///:~
