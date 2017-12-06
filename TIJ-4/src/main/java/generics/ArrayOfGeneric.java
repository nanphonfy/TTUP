package generics;//: generics/ArrayOfGeneric.java

public class ArrayOfGeneric {
    static final int SIZE = 100;
    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /**
         * 无论持有何种类型，都具有相同结构，创建一个Objet数组
         * 并将其转型为所希望的数组类型，将产生异常
         */
        // Compiles; java.lang.ClassCastException:
        //gia = (Generic<Integer>[])new Object[SIZE];
        // Runtime type is the raw (erased) type:
        gia = (Generic<Integer>[]) new Generic[SIZE];
        System.out.println(gia.getClass().getSimpleName());
        gia[0] = new Generic<>();
        //gia[1] = new Object(); // Compile-time error
        // Discovers type mismatch at compile time:
        //gia[2] = new Generic<Double>();
    }
} /* Output:
Generic[]
*///:~
