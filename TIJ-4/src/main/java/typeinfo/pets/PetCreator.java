//: typeinfo/pets/PetCreator.java
// Creates random sequences of Pets.
package typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



/**
 * 可随机创建不同类型的宠物，还可创建宠物数组和List
 * 为使工具能够适用多种不同实现，定义为抽象类
 */
public abstract class PetCreator {
    private Random rand = new Random(47);

    /**
     * 类型被指定为“任何从Pet导出的类”
     */
    // The List of the different types of Pet to create:
    public abstract List<Class<? extends Pet>> types();

    public Pet randomPet() { // Create one random Pet
        int n = rand.nextInt(types().size());
        try {
            /**
             * 因此newInstance不需转型即可产生Pet
             */
            return types().get(n).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Pet[] createArray(int size) {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++) {
            result[i] = randomPet();
        }
        return result;
    }

    public ArrayList<Pet> arrayList(int size) {
        ArrayList<Pet> result = new ArrayList<>();
        Collections.addAll(result, createArray(size));
        return result;
    }
} ///:~
