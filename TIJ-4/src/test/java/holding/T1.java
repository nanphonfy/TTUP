package holding;

import net.mindview.util.TextFile;
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

    /**
     * LinkedList还添加了可用做栈、队列、双端队列的方法
     */
    @Test
    public void LinkedListFeatures(){
        LinkedList<Pet> pets = new LinkedList<>(Pets.arrayList(5));
        //[Rat, Manx, Cymric, Mutt, Pug]
        print(pets);
        // Identical:
        /*
        getFirst和element完全一样，都返回列表第一个元素，不移除，若空，则抛出NoSuchElementException
        pets.getFirst(): Rat
        pets.element(): Rat*/
        print("pets.getFirst(): " + pets.getFirst());
        print("pets.element(): " + pets.element());
        // Only differs in empty-list behavior:
        //列表空的时候，返回null
        print("pets.peek(): " + pets.peek());
        // Identical; remove and return the first element
        /*完全一样，移除并返回列表的头，空时抛NoSuchElementException
        pets.remove(): Rat
        pets.removeFirst(): Manx*/
        print("pets.remove(): " + pets.remove());
        print("pets.removeFirst(): " + pets.removeFirst());
        // Only differs in empty-list behavior:
        /*列表空的时候，返回null
        pets.poll(): Cymric*/
        print("pets.poll(): " + pets.poll());
        //[Mutt, Pug]
        print(pets);

        //addFirst插入列表头部
        pets.addFirst(new Rat());
        //After addFirst(): [Rat, Mutt, Pug]
        print("After addFirst(): " + pets);
        //offer、add和addLast一样，都是插入列表尾部
        pets.offer(Pets.randomPet());
        //After offer(): [Rat, Mutt, Pug, Cymric]
        print("After offer(): " + pets);
        pets.add(Pets.randomPet());
        //After add(): [Rat, Mutt, Pug, Cymric, Pug]
        print("After add(): " + pets);
        pets.addLast(new Hamster());
        //After addLast(): [Rat, Mutt, Pug, Cymric, Pug, Hamster]
        print("After addLast(): " + pets);
        //pets.removeLast(): Hamster
        print("pets.removeLast(): " + pets.removeLast());
    }

    /**
     * 栈，又称叠加栈，后进先出（LIFO）的容器
     * LinkedList可直接作为栈使用
     */
    @Test
    public void StackTestTest(){
        //java.util的Stack，Stack<E> extends Vector<E>，是java1.0的产物，设计欠佳
        //用LinkedList可产生更好的Stack
        net.mindview.util.Stack<String> stack = new net.mindview.util.Stack<>();
        for (String s : "My dog has fleas".split(" ")) {
            stack.push(s);
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    /**
     * Set（就是Collection，继承和多态的典型应用：表现不同）不保存重复元素
     * HashSet对快速查找进行优化
     */
    @Test
    public void SetOfIntegerTest(){
        Random rand = new Random(47);
        //HashSet出于速度考虑，使用了散列函数
        Set<Integer> intset = new HashSet<>();
        for(int i = 0; i < 10000; i++) {
            intset.add(rand.nextInt(30));
        }
        System.out.println(intset);
    }

    /**
     * 将元素存储在红-黑树数据结构中，可对结果排序
     */
    @Test
    public void SortedSetOfIntegerTest(){
        Random rand = new Random(47);
        SortedSet<Integer> intset = new TreeSet<>();
        for(int i = 0; i < 10000; i++) {
            intset.add(rand.nextInt(30));
        }
        //[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
        System.out.println(intset);
    }

    /**
     * 使用contains测试Set的归属性
     * eg.用圆表示集合与集合之间的关系
     */
    @Test
    public void SetOperationsTest(){
        Set<String> set1 = new HashSet<>();
        Collections.addAll(set1,"A B C D E F G H I J K L".split(" "));
        set1.add("M");
        //H: true
        print("H: " + set1.contains("H"));
        //N: false
        print("N: " + set1.contains("N"));
        Set<String> set2 = new HashSet<>();
        Collections.addAll(set2, "H I J K L".split(" "));
        //set2 in set1: true
        print("set2 in set1: " + set1.containsAll(set2));
        set1.remove("H");
        //set1: [A, B, C, D, E, F, G, I, J, K, L, M]
        print("set1: " + set1);
        //set2 in set1: false
        print("set2 in set1: " + set1.containsAll(set2));
        set1.removeAll(set2);
        // [A, B, C, D, E, F, G, M]
        print("set2 removed from set1: " + set1);
        Collections.addAll(set1, "X Y Z".split(" "));
        //'X Y Z' added to set1: [A, B, C, D, E, F, G, M, X, Y, Z]
        print("'X Y Z' added to set1: " + set1);
    }

    /**
     * 打开文件，列出其所含单词
     */
    @Test
    public void UniqueWordsTest(){
        //正则表达式，"\\W+"：一个或多个字母
        //TreeSet按字典排序
        Set<String> words = new TreeSet<>(new TextFile("pom.xml", "\\W+"));
        //[0, 1, 12, 2001, 4, 6, 7, 8, POM, SNAPSHOT, TIJ4, UTF, XMLSchema, apache, artifactId, build, cn, code, compiler, configuration, dependencies, dependency, encoding, groupId, http, instance, junit, log4j12, maven, modelVersion, nanphonfy, org, plugin, plugins, project, properties, schemaLocation, scope, slf4j, source, target, test, version, w3, www, xml, xmlns, xsd, xsi]
        System.out.println(words);
    }

    /**
     * 想改为按字母序排序，可使用String.CASE_INSENSITIVE_ORDER比较器
     */
    @Test
    public void UniqueWordsAlphabeticTest(){
        Set<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        words.addAll(new TextFile("pom.xml", "\\W+"));
        //[0, 1, 12, 2001, 4, 6, 7, 8, apache, artifactId, build, cn, code, compiler, configuration, dependencies, dependency, encoding, groupId, http, instance, junit, log4j12, maven, modelVersion, nanphonfy, org, plugin, plugins, POM, project, properties, schemaLocation, scope, slf4j, SNAPSHOT, source, target, test, TIJ4, UTF, version, w3, www, xml, xmlns, XMLSchema, xsd, xsi]
        System.out.println(words);
    }

    /**
     * 测试类的随机性
     */
    @Test
    public void StatisticsTest(){
        Random rand = new Random(47);
        //HashMap自动包装机制，可使用Integer引用，不能使用基本类型
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            // Produce a number between 0 and 20:
            int r = rand.nextInt(20);
            Integer freq = m.get(r);
            m.put(r, freq == null ? 1 : freq + 1);
        }
        //{0=481, 1=502, 2=489, 3=508, 4=481, 5=503, 6=519, 7=471, 8=468, 9=549, 10=513, 11=531, 12=521, 13=506, 14=477, 15=497, 16=533, 17=509, 18=478, 19=464}
        System.out.println(m);
    }

    /**
     *containsKey和containsValue
     */
    @Test
    public void PetMapTest(){
        Map<String, Pet> petMap = new HashMap<>();
        petMap.put("My Cat", new Cat("Molly"));
        petMap.put("My Dog", new Dog("Ginger"));
        petMap.put("My Hamster", new Hamster("Bosco"));
        //{My Dog=Dog Ginger, My Cat=Cat Molly, My Hamster=Hamster Bosco}
        print(petMap);
        Pet dog = petMap.get("My Dog");
        //Dog Ginger
        print(dog);
        //true
        print(petMap.containsKey("My Dog"));
        //true
        print(petMap.containsValue(dog));
    }

    /**
     * 组合容器，生成强大的数据结构
     */
    @Test
    public void MapOfListTest(){
        Map<Person, List<? extends Pet>> petPeople = new HashMap<>();

        petPeople.put(new Person("Dawn"), Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
        petPeople.put(new Person("Kate"),
                Arrays.asList(new Cat("Shackleton"), new Cat("Elsie May"), new Dog("Margrett")));
        petPeople.put(new Person("Marilyn"),
                Arrays.asList(new Pug("Louie aka Louis Snorkelstein Dupree"), new Cat("Stanford aka Stinky el Negro"),new Cat("Pinkola")));
        petPeople.put(new Person("Luke"), Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
        petPeople.put(new Person("Isaac"), Arrays.asList(new Rat("Freckly")));

        //People: [Person Marilyn, Person Dawn, Person Luke, Person Isaac, Person Kate]
        //返回它的键的Set
        print("People: " + petPeople.keySet());
        //Pets: [[Pug Louie aka Louis Snorkelstein Dupree, Cat Stanford aka Stinky el Negro, Cat Pinkola], [Cymric Molly, Mutt Spot], [Rat Fuzzy, Rat Fizzy], [Rat Freckly], [Cat Shackleton, Cat Elsie May, Dog Margrett]]
        //返回它的值的Collection
        print("Pets: " + petPeople.values());
        for (Person person : petPeople.keySet()) {
            print(person + " has:");
            for (Pet pet : petPeople.get(person)) {
                print("    " + pet);
            }
        }
    }

    /**
     * 队列，先进先出（FIFO）的容器，在并发编程特别重要，它可安全将对象从一个任务传输给另一个任务
     * LinkedList提供方法支持队列行为，实现了Queue接口，可作为Queue的一种实现
     */
    @Test
    public void QueueDemoTest(){
        //Queue接口窄化了对LinkedList方法的访问权限
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < 10; i++) {
            //offer：插入队尾或返回false；
            queue.offer(i);
        }
        //0 1 2 3 4 5 6 7 8 9
        printQ(queue);
        Queue<Character> qc = new LinkedList<>();
        for(char c : "Brontosaurus".toCharArray()) {
            qc.offer(c);
        }
        //B r o n t o s a u r u s
        printQ(qc);
    }
    public void printQ(Queue queue) {
        //peek（空时返回null）和element（空时返回NoSuchElementException）都不移除的返回对头
        while(queue.peek() != null) {
            //poll（空时返回null）和remove（空时返回NoSuchElementException）移除并返回队头
            System.out.print(queue.remove() + " ");
        }
        System.out.println();
    }

    /**
     * 优先级队列（jdk1.5）来实现最高优先级的元素最优先处理，
     * 当插入一个元素时，会在队列中被排序，默认自然排序
     * （算法维护一个堆，移除时选择最重要的元素）
     * 最小值拥有最高优先级
     */
    @Test
    public void PriorityQueueDemoTest(){
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>();
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++) {
            priorityQueue.offer(rand.nextInt(i + 10));
        }
        //0 1 1 1 1 1 3 5 8 14
        printQ(priorityQueue);

        List<Integer> ints = Arrays.asList(25, 22, 20,18, 14, 9, 3, 1, 1, 2, 3, 9, 14, 18, 21, 23, 25);
        priorityQueue = new PriorityQueue<>(ints);
        //1 1 2 3 3 9 9 14 14 18 18 20 21 22 23 25 25
        printQ(priorityQueue);
        priorityQueue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        priorityQueue.addAll(ints);
        //25 25 23 22 21 20 18 18 14 14 9 9 3 3 2 1 1
        printQ(priorityQueue);

        String fact = "EDUCATION SHOULD ESCHEW OBFUSCATION";
        List<String> strings = Arrays.asList(fact.split(""));
        PriorityQueue<String> stringPQ = new PriorityQueue<>(strings);
        //      A A B C C C D D E E E F H H I I L N N O O O O S S S T T U U U W
        printQ(stringPQ);
        stringPQ = new PriorityQueue<>(strings.size(), Collections.reverseOrder());
        stringPQ.addAll(strings);
        //W U U U T T S S S O O O O N N L I I H H F E E E D D C C C B A A
        printQ(stringPQ);

        Set<Character> charSet = new HashSet<>();
        for(char c : fact.toCharArray()) {
            charSet.add(c); // Autoboxing
        }
        PriorityQueue<Character> characterPQ = new PriorityQueue<>(charSet);
        //  A B C D E F H I L N O S T U W
        printQ(characterPQ);
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

