package innerclasses;
//: innerclasses/ClassInInterface.java
// {main: ClassInInterface$Test}

public interface ClassInInterface {
    void howdy();

    /**
     * 放到接口中的任何类都自动地是public和static
     */
    class Test implements ClassInInterface {
        @Override public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
} /* Output:
Howdy!
*///:~
