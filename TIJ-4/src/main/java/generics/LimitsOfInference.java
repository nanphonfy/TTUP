package generics;
//: generics/LimitsOfInference.java

import typeinfo.pets.Person;
import typeinfo.pets.Pet;

import java.util.List;
import java.util.Map;

public class LimitsOfInference {
    static void f(Map<Person, List<? extends Pet>> petPeople) {
    }

    public static void main(String[] args) {
        /*f(java.util.Map<typeinfo.pets.Person,java.util.List<? extends typeinfo.pets.Pet>>)
        in LimitsOfInference cannot be appliedto(java.util.Map<java.lang.Object,java.lang.Object>)*/
        //f(New.map()); // Does not compile
    }
} ///:~
