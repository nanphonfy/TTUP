package generics;
//: generics/LostInformation.java

import java.util.*;

class Frob {
}

class Fnorkle {
}

class Quark<Q> {
}

class Particle<POSITION, MOMENTUM> {
}

public class LostInformation {
    public static void main(String[] args) {
        List<Frob> list = new ArrayList<>();
        Map<Frob, Fnorkle> map = new HashMap<>();
        Quark<Fnorkle> quark = new Quark<>();
        Particle<Long, Double> p = new Particle<>();
        //[E]
        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        //[K, V]
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        //[Q]
        System.out.println(Arrays.toString(quark.getClass().getTypeParameters()));
        //[POSITION, MOMENTUM]
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));
    }
} /* Output:
[E]
[K, V]
[Q]
[POSITION, MOMENTUM]
*///:~
