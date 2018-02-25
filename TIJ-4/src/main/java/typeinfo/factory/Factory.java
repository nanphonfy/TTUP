//: typeinfo/factory/Factory.java
package typeinfo.factory;

/**
 * 泛型T使得create可在每种Factory实现中返回不同的类型，充分利用了协变返回类型。
 *
 * @param <T>
 */
public interface Factory<T> {
    T create();
} ///:~
