package typeinfo;//: typeinfo/ModifyingPrivateFields.java

import java.lang.reflect.Field;

class WithPrivateFinalField {
    private int i = 1;
    private final String s = "I'm totally safe";
    private String s2 = "Am I safe?";

    @Override
    public String toString() {
        return "i = " + i + ", " + s + ", " + s2;
    }
}

public class ModifyingPrivateFields {
    public static void main(String[] args) throws Exception {
        WithPrivateFinalField pf = new WithPrivateFinalField();
        //i = 1, I'm totally safe, Am I safe?
        System.out.println(pf);

        Field f = pf.getClass().getDeclaredField("i");
        f.setAccessible(true);
        //f.getInt(pf): 1
        System.out.println("f.getInt(pf): " + f.getInt(pf));
        f.setInt(pf, 47);
        //i = 47, I'm totally safe, Am I safe?
        System.out.println(pf);
        f = pf.getClass().getDeclaredField("s");
        f.setAccessible(true);
        //f.get(pf): I'm totally safe
        System.out.println("f.get(pf): " + f.get(pf));
        //final变量是安全的,final域遭遇修改时是安全的，实际上不会发生任何修改。
        f.set(pf, "No, you're not!");
        //i = 47, I'm totally safe, Am I safe?
        System.out.println(pf);
        f = pf.getClass().getDeclaredField("s2");
        f.setAccessible(true);
        //f.get(pf): Am I safe?
        System.out.println("f.get(pf): " + f.get(pf));
        f.set(pf, "No, you're not!");
        //i = 47, I'm totally safe, No, you're not!
        System.out.println(pf);
    }
}
/* Output:
i = 1, I'm totally safe, Am I safe?
f.getInt(pf): 1
i = 47, I'm totally safe, Am I safe?
f.get(pf): I'm totally safe
i = 47, I'm totally safe, Am I safe?
f.get(pf): Am I safe?
i = 47, I'm totally safe, No, you're not!
*///:~
