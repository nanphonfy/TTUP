package typeinfo;
//: typeinfo/Person.java
// A class with a Null Object.

import net.mindview.util.Null;

class Person {
    public final String first;
    public final String last;
    public final String address;

    // etc.
    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person: " + first + " " + last + " " + address;
    }

    /**
     * 这使得instanceof可探测空对象，不需要在所有类都添加isNull方法。
     */
    public static class NullPerson extends Person implements Null {
        private NullPerson() {
            super("None", "None", "None");
        }

        @Override
        public String toString() {
            return "NullPerson";
        }
    }

    /**
     * 通常空对象都是单例的，因此用静态final实例创建。
     * 若想修改NullPersion，只能用新的Person对象替换它
     * ①由于是单例，所以还可使用equals甚至==来与Person.Null比较；
     * ②instanceof探测泛化的Null还是更具体的NullPerson。
     */
    public static final Person NULL = new NullPerson();
} ///:~
