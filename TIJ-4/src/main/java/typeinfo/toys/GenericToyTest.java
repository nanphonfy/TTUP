//: typeinfo/toys/GenericToyTest.java
// Testing class Class.
package typeinfo.toys;

public class GenericToyTest {
    public static void main(String[] args) throws Exception {
        Class<FancyToy> ftClass = FancyToy.class;
        // Produces exact type:
        FancyToy fancyToy = ftClass.newInstance();

        //返回的是基类（不是接口），如果是超类，编译器允许声明某个类是FancyToy的超类
        Class<? super FancyToy> up = ftClass.getSuperclass();

        //Class<Toy>不仅仅是 某个类是FancyToy的超类
        // This won't compile:
        //Class<Toy> up2 = ftClass.getSuperclass();

        //正因为含糊性，所以newInstance返回的不是精确类型，而是Object
        // Only produces Object:
        Object obj = up.newInstance();
    }
} ///:~
