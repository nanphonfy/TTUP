package innerclasses;

//: innerclasses/Parcel2.java
// Returning a reference to an inner class.
public class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents contents() {
        return new Contents();
    }

    public void ship(String dest) {
        //外部类有一个方法，返回一个指向内部类的引用,eg.contents、to方法
        Contents c = contents();
        Destination d = to(dest);
        //11,Tasmania
        System.out.println(String.format("%d,%s", c.value(), d.readLabel()));
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");
        Parcel2 q = new Parcel2();
        // Defining references to inner classes:
        Parcel2.Contents c = q.contents();
        Parcel2.Destination d = q.to("Borneo");
    }
} /* Output:
Tasmania
*///:~
