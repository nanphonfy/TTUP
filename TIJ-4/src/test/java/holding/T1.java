package holding;

import org.junit.Test;
import typeinfo.pets.*;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * @author nanphonfy(南风zsr)
 * @date 2017/11/6
 */
public class T1 extends AbstractTest {
    /**
     * 没有用泛型
     * 集合保存的是Object，没有使用泛型（java泛型->类型安全容器）下，不同对象都可放入容器，编译|运行都没有问题。
     * get时得到Object的引用，需强制转型，运行时会异常。
     */
    @Test
    public void ApplesAndOrangesWithoutGenericsTest() {
        ArrayList apples = new ArrayList();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }
        // Not prevented from adding an Orange to apples:
        apples.add(new Orange());
        for (int i = 0; i < apples.size(); i++) {
            //java.lang.ClassCastException: holding.Orange cannot be cast to holding.Apple
            ((Apple) apples.get(i)).id();
        }
        // Orange is detected only at run time
    }

    /**
     * 使用泛型
     * 使用泛型后，编译器会检测放入容器的对象类型，将其变成编译期错误，而不是运行时错误。
     * List知道它保存的类型，get时帮我们转型。
     */
    @Test
    public void ApplesAndOrangesWithGenericsTest() {
        ArrayList<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            apples.add(new Apple());
        }
        // Compile-time error:
        //apples.add(new Orange());
        long start = System.nanoTime();
        for (int i = 0; i < apples.size(); i++) {
            System.out.print(apples.get(i).id() + " ");
        }
        System.out.println(System.nanoTime()-start);

        start = System.nanoTime();
        // Using foreach:不需使用每个元素的索引时
        for (Apple c : apples) {
            System.out.print(c.id() + " ");
        }
        System.out.println(System.nanoTime()-start);
    }

    /**
     *
     */
    @Test
    public void GenericsAndUpcastingTest() {
        /*class GrannySmith extends Apple {}
        class Gala extends Apple {}
        class Fuji extends Apple {}
        class Braeburn extends Apple {}*/
        ArrayList<Apple> apples = new ArrayList<Apple>();
        apples.add(new GrannySmith());
        apples.add(new Gala());
        apples.add(new Fuji());
        apples.add(new Braeburn());
        for(Apple c : apples) {
            //打印的是Object默认的toString方法，类名后跟随该对象的散列码（无符号16进制，通过hashCode方法产生）
            //holding.GrannySmith@5aaa6d82 holding.Gala@73a28541 holding.Fuji@6f75e721 holding.Braeburn@69222c14
            System.out.print(c+" ");
        }
    }

    /**
     * java容器用途：保存对象。
        划分为两个不同的概念：
        Collection：独立元素的序列，eg.List按插入顺序保存，Set不能有重复，Queue按排队规则（被插入顺序）。
        Map："键值对"，映射表允许我们用另一个对象查找某对象，也称"关联数组"或"字典"（可用键对象查找值对象）。
     */
    @Test
    public void SimpleCollectionTest(){
        /**
         * Collection概括了序列的概念——一种存放一组对象的方式
         * 所有继承Collection的类都可正常工作
         */
        Collection<Integer> c = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            c.add(i); // Autoboxing
        }
        //所有Collection都可用foreach遍历
        for(Integer i : c) {
            System.out.print(i + ", ");
        }
    }

    /**
     * 添加一组元素
     *
     */
    @Test
    public void AddingGroupsTest(){
        //接受一个数组或一个用逗号分隔的元素列表，转为List
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Integer[] moreInts = { 6, 7, 8, 9, 10 };
        //不如Collections.addAll或Arrays.asList灵活
        collection.addAll(Arrays.asList(moreInts));

        // Collections.addAll接受一个Collection对象、一个数组（运行更快）或一个用逗号分隔的元素列表（运行更快）
        // Runs significantly faster, but you can'tconstruct a Collection this way:以下两种推荐使用
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        Collections.addAll(collection, moreInts);

        // Produces a list "backed by" an array:
        // 底层是数组，不能调整尺寸
        List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
        list.set(1, 99); // OK -- modify an element
        //java.lang.UnsupportedOperationException
        // Runtime error because the underlying array cannot be resized.
        //list.add(21);
    }

    /**
     * Arrays.asList的限制：对产生的list的类型做了最理想的假设，有时会引发问题
     */
    @Test
    public void AsListInferenceTest(){
        /*class Snow {}
        class Light extends holding.Powder {}
        class Heavy extends holding.Powder {}
        class Powder extends holding.Snow {}
        class Crusty extends holding.Snow {}
        class Slush extends holding.Snow {}*/
        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());


        //Arrays.asList只有Powder类型，从第一个参数了解到目标类型
        // Won't compile:
        // List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
        // Compiler says:
        // found   : java.util.List<Powder>
        // required: java.util.List<Snow>

        // Collections.addAll() doesn't get confused:
        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3, new Light(), new Heavy());

        // Give a hint using an explicit type argument specification:
        //显示类型参数说明：插入一条线索，告诉编译器参数的目标类型是什么
        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy());
    }

    /**
     * 容器的打印
     * 区别：容器中每个"槽"保存的元素个数。Collection每个槽只能保存一个元素，Map每个槽可保存两个，即键值。
     */
    @Test
    public void PrintingContainersTest(){
        /**
         * 打印容器无需任何帮助，Collection默认用方括号，Map默认用大括号
         */
        /*[rat, cat, dog, dog]
        [rat, cat, dog, dog]*/
        print(fill(new ArrayList<String>()));
        //LinkedList有更多的方法
        print(fill(new LinkedList<String>()));
        /*[rat, cat, dog]
        [cat, dog, rat]
        [rat, cat, dog]*/
        //HashSet使用相当复杂的方式存储元素
        print(fill(new HashSet<String>()));//查询最快
        print(fill(new TreeSet<String>()));
        print(fill(new LinkedHashSet<String>()));
        /*{rat=Fuzzy, cat=Rags, dog=Spot}
        {cat=Rags, dog=Spot, rat=Fuzzy}
        {rat=Fuzzy, cat=Rags, dog=Spot}*/
        //Map会自动调整尺寸，还知道如何打印自己
        print(fill(new HashMap<String, String>()));//查询最快
        print(fill(new TreeMap<String, String>()));
        print(fill(new LinkedHashMap<String, String>()));
    }
    static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }
    static Map fill(Map<String,String> map) {
        map.put("rat", "Fuzzy");
        map.put("cat", "Rags");
        map.put("dog", "Bosco");
        map.put("dog", "Spot");
        return map;
    }

    /**
     * List
     * ArrayList随机访问快，插入移除较慢；
     * LinkedList随机访问较慢，插入移除快，特性集更大；
     */
    @Test
    public void ListFeaturesTest(){
        Random rand = new Random(47);
        List<Pet> pets = Pets.arrayList(7);
        //1: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug]
        print("1: " + pets);
        Hamster h = new Hamster();
        pets.add(h); // Automatically resizes
        //可修改序列, 2: [Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Hamster]
        print("2: " + pets);
        //是否包含，3: true
        print("3: " + pets.contains(h));
        //传对象引用移除
        pets.remove(h); // Remove by object
        Pet p = pets.get(2);
        //所处位置索引编号, 4: Cymric 2
        print("4: " +  p + " " + pets.indexOf(p));
        Pet cymric = new Cymric();
        //5: -1
        print("5: " + pets.indexOf(cymric));
        //6: false
        print("6: " + pets.remove(cymric));
        // Must be the exact object:
        //7: true
        print("7: " + pets.remove(p));
        //8: [Rat, Manx, Mutt, Pug, Cymric, Pug]
        print("8: " + pets);
        pets.add(3, new Mouse()); // Insert at an index
        //9: [Rat, Manx, Mutt, Mouse, Pug, Cymric, Pug]
        print("9: " + pets);
        List<Pet> sub = pets.subList(1, 4);
        //subList: [Manx, Mutt, Mouse]
        print("subList: " + sub);
        //10: true
        print("10: " + pets.containsAll(sub));
        Collections.sort(sub); // In-place sort
        //sorted subList: [Manx, Mouse, Mutt]
        print("sorted subList: " + sub);
        // Order is not important in containsAll():
        //11: true
        print("11: " + pets.containsAll(sub));
        Collections.shuffle(sub, rand); // Mix it up
        //shuffled subList: [Mouse, Manx, Mutt]
        print("shuffled subList: " + sub);
        //12: true
        print("12: " + pets.containsAll(sub));
        List<Pet> copy = new ArrayList<Pet>(pets);
        sub = Arrays.asList(pets.get(1), pets.get(4));
        //sub: [Mouse, Pug]
        //取交集，行为依赖于equals方法
        print("sub: " + sub);
        copy.retainAll(sub);
        //13: [Mouse, Pug]
        print("13: " + copy);
        copy = new ArrayList<Pet>(pets); // Get a fresh copy
        copy.remove(2); // Remove by index
        //14: [Rat, Mouse, Mutt, Pug, Cymric, Pug]
        print("14: " + copy);
        //也依赖于equals方法
        copy.removeAll(sub); // Only removes exact objects
        //15: [Rat, Mutt, Cymric, Pug]
        print("15: " + copy);
        copy.set(1, new Mouse()); // Replace an element
        //16: [Rat, Mouse, Cymric, Pug]
        print("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        //17: [Rat, Mouse, Mouse, Pug, Cymric, Pug]
        print("17: " + copy);
        //18: false
        print("18: " + pets.isEmpty());
        pets.clear(); // Remove all elements
        //19: []
        print("19: " + pets);
        //20: true
        print("20: " + pets.isEmpty());
        pets.addAll(Pets.arrayList(4));
        //21: [Manx, Cymric, Rat, EgyptianMau]
        print("21: " + pets);
        Object[] o = pets.toArray();
        //22: EgyptianMau
        print("22: " + o[3]);
        Pet[] pa = pets.toArray(new Pet[0]);
        //23: 14
        print("23: " + pa[3].id());
    }

    /**
     * 迭代器
     * 容器缺点：面向确切类型编程。
     * 迭代器（也是一种设计模式，通常被称为轻量级对象，创建代价小）可不关心容器的类型。
     */
    @Test
    public void SimpleIterationTest(){
        List<Pet> pets = Pets.arrayList(12);

        //返回一个Iterator，将准备好返回序列的第一个元素
        Iterator<Pet> it = pets.iterator();
        //获得序列下一个元素
        while (it.hasNext()) {
            //检查序列中是否还有元素
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        //0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx 8:Cymric 9:Rat 10:EgyptianMau 11:Hamster
        System.out.println();
        // A simpler approach, when possible:
        //若只向前遍历List，而不打算修改List本身，可用foreach（更简洁）
        for (Pet p : pets) {
            System.out.print(p.id() + ":" + p + " ");
        }
        //0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx 8:Cymric 9:Rat 10:EgyptianMau 11:Hamster
        System.out.println();
        // An Iterator can also remove elements:
        it = pets.iterator();
        for (int i = 0; i < 6; i++) {
            //在remove之前必须先调用next方法
            it.next();
            //将迭代器新返回的元素删除
            it.remove();
        }
        //[Pug, Manx, Cymric, Rat, EgyptianMau, Hamster]
        System.out.println(pets);
    }

    /**
     * 迭代器统一了对容器的访问方式
     * 创建display，不必知晓容器的确切类型
     */
    @Test
    public void CrossContainerIterationTest(){
        ArrayList<Pet> pets = Pets.arrayList(8);
        LinkedList<Pet> petsLL = new LinkedList<>(pets);
        HashSet<Pet> petsHS = new HashSet<>(pets);
        TreeSet<Pet> petsTS = new TreeSet<>(pets);
        //Iterator：能够将遍历序列的操作与序列底层结构分离
        //0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
        display(pets.iterator());
        //0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
        display(petsLL.iterator());
        //0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
        display(petsHS.iterator());
        //5:Cymric 2:Cymric 7:Manx 1:Manx 3:Mutt 6:Pug 4:Pug 0:Rat
        display(petsTS.iterator());
    }

    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    @Test
    public void ListIterationTest(){
        List<Pet> pets = Pets.arrayList(8);
        ListIterator<Pet> it = pets.listIterator();
        while (it.hasNext()) {
            //可指向当前位置的前一个和后一个元素的索引
            //Rat, 1, 0; Manx, 2, 1; Cymric, 3, 2; Mutt, 4, 3; Pug, 5, 4; Cymric, 6, 5; Pug, 7, 6; Manx, 8, 7;
            System.out.print(it.next() + ", " + it.nextIndex() + ", " + it.previousIndex() + "; ");
        }
        System.out.println();
        // Backwards:
        while (it.hasPrevious()) {
            //7 6 5 4 3 2 1 0
            System.out.print(it.previous().id() + " ");
        }
        System.out.println();
        //[Rat, Manx, Cymric, Mutt, Pug, Cymric, Pug, Manx]
        System.out.println(pets);
        it = pets.listIterator(3);
        while (it.hasNext()) {
            it.next();
            //可用set替换它访问过的最后一个元素
            it.set(Pets.randomPet());
        }
        //[Rat, Manx, Cymric, Cymric, Rat, EgyptianMau, Hamster, EgyptianMau]
        System.out.println(pets);
    }

    @Test
    public void Test(){

    }
}

class Apple {
    private static long counter;
    private final long id = counter++;

    public long id() {
        return id;
    }
}

class Orange {
}

class GrannySmith extends Apple {}
class Gala extends Apple {}
class Fuji extends Apple {}
class Braeburn extends Apple {}

class Snow {}
class Powder extends Snow {}
class Light extends Powder {}
class Heavy extends Powder {}
class Crusty extends Snow {}
class Slush extends Snow {}

