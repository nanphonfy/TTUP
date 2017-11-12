package innerclasses;
//: c08:Parcel4.java
// Nesting a class within a method.

interface Destination {
    String readLabel();
}

interface Contents {
    int value();
}

public class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;

        @Override public int value() {
            return i;
        }
    }

    /**
     * 只有Parcel4及其子类，还有同一包名的类可访问
     */
    protected class PDestination implements Destination {
        private String label;

        PDestination(String whereTo) {
            label = whereTo;
        }

        @Override public String readLabel() {
            return label;
        }
    }

    public Destination destination(String s) {
        return new PDestination(s);
    }

    public Contents contents() {
        return new PContents();
    }

    public static void main(String[] args) {
        Parcel4 p = new Parcel4();
        Destination d = p.destination("kangkang");
        Contents c = p.contents();

        Parcel4.PDestination ppd = p.new PDestination("kk");
        //外部类对象可以创建私有内部类对象
        //Parcel4.PContents ppc = p.new PContents();
    }
} ///:~
