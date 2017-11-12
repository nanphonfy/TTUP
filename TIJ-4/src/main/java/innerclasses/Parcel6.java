package innerclasses;
//: innerclasses/Parcel6.java
// Nesting a class within a scope.

public class Parcel6 {
    private void internalTracking(boolean b) {
        // 嵌入在if语句作用域内的TrackingSlip，其实与别的类一起编译过了，只不过在作用域外，它不可用
        // 除此之外，它和普通类一样
        if (b) {
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
        }
        // Can't use it here! Out of scope:
        //! TrackingSlip ts = new TrackingSlip("x");
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        p.track();
    }
} ///:~
