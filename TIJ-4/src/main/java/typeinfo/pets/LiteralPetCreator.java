//: typeinfo/pets/LiteralPetCreator.java
// Using class literals.
package typeinfo.pets;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {
    // No try block needed.
    @SuppressWarnings("unchecked")
    /**
     * 用所有Pet类型预加载Map（只是那些要随机生成的类型），故allTypes List必需
     */
    public static final List<Class<? extends Pet>> allTypes = Collections.unmodifiableList(Arrays.asList(Pet.class, Dog.class, Cat.class,
            Rodent.class, Mutt.class, Pug.class,EgyptianMau.class, Manx.class, Cymric.class, Rat.class, Mouse.class, Hamster.class));

    /**
     * 包含确切宠物类型，用于随机生成宠物
     * types会在编译时得到检查，无需try catch，和Class.forName不一样
     * Types for random creation:
     */
    private static final List<Class<? extends Pet>> types = allTypes.subList(allTypes.indexOf(Mutt.class), allTypes.size());

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }

    public static void main(String[] args) {
        System.out.println(types);
    }
}
/* Output:
[class typeinfo.pets.Mutt, class typeinfo.pets.Pug, class typeinfo.pets.EgyptianMau, class typeinfo.pets.Manx, class typeinfo.pets.Cymric, class typeinfo.pets.Rat, class typeinfo.pets.Mouse, class typeinfo.pets.Hamster]
*///:~
