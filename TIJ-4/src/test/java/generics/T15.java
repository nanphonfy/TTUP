package generics;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.*;
import org.junit.Test;

import java.util.*;

import static generics.GenericVarargs.makeList;

/**
 * @author nanphonfy(南风zsr)
 * @date 2017/11/24
 */
public class T15 {
    /**
     * 【介绍】
     * 一般类和方法只能使用具体类型（基本类型或自定义类），多态（基类或接口）也算是一种泛化机制，
     * 但有时即使使用接口，也对程序又太强约束（代码必须使用特定接口）。
     * java5的泛型（时代码更直接更优雅）实现了参数化类型，编译器负责转型操作且保证类型的正确性。
     */

    /**
     * 【简单泛型】
     * 容器类：最具重用性的类库之一
     * 只能持有单个对象（明确指定持有对象的类型）的类
     */
    @Test
    public void Holder1Test(){

    }

    /**
     *让类持有Object类型的对象
     * Holder2可存储任何类型的对象
     */
    @Test
    public void Holder2Test(){

    }

    /**
     * 泛型：用来指定容器要持有什么类型的对象，且由编译器来保证类型的正确性
     * 创建对象时，必须指明想持有什么类型的对象，将其置于尖括号内
     */
    @Test
    public void Holder3Test(){

    }

    /**
     * 【一个元组类库】
     * 仅一个方法调用就能返回多个对象
     * 元组（可具有任意长度，元组中的对象可是任意类型）：将一组对象直接打包存储在单一对象中
     * 2维元组（持有两个对象）
     */
    @Test
    public void TupleTest(){
        TwoTuple twoTuple=new TwoTuple("2",1);
        //使用继承，实现长度更长的元组
        ThreeTuple threeTuple=new ThreeTuple(1.2,false,"kk");
    }

    /**
     * 使用元组：定义一个长度适合的元组，将其作为返回值
     * final声明能保护public元素，被构造出来后，就不能再被赋值
     */
    @Test
    public void TupleTestTest(){

    }

    /**
     * 【一个堆栈类】
     * 传统的下推堆栈
     * 不用LinkeList，实现自己的内部链式存储机制
     * 使用了末端哨兵（在构造LinkedStack时创建），判断堆栈何时为空，然后每次调用push，就会创建一个Node<T>对象，并将其链接到前一个Node<T>对象
     */
    @Test
    public void LinkedStackTest(){

    }

    /**
     * 持有特定类型对象的列表，可应用于各种类型的对象的工具
     */
    @Test
    public void RandomListTest(){
        RandomList<String> rs = new RandomList<>();
        for (String s : ("The quick brown fox jumped over the lazy brown dog").split(" ")) {
            rs.add(s);
        }
        for (int i = 0; i < 11; i++) {
            System.out.print(rs.select() + " ");
        }
    }

    /**
     * 【泛型接口】
     * eg.生成器：一种专门负责创建对象的类（工厂方法设计模式的一种应用）
     * 工厂方法一般需要参数，而它不需要，就可创建对象
     * （接口使用泛型与类使用泛型没区别）
     * 实现Generator<Coffee>接口，能够随机生成不同类型的Coffee对象
     * 参数化的Generator<Coffee>接口确保next返回值是参数的类型
     * 实现Iterable接口，可在循环语句使用
     */
    @Test
    public void CoffeeGeneratorTest(){
        Generator generator;
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }
    }

    /**
     * Fibonacci类里里外外都是使用int，但类型参数却是Integer
     * 泛型局限性：基本类型无法作为类型参数
     */
    @Test
    public void FibonacciTest(){

    }

    /**
     * 有多种方法可实现适配器（eg.通过继承创建适配器类）
     * 要在类的循环语句使用IterableFibonacci，必须向IterableFibonacci构造器提供一个边界值，才能知道haseNext何时返回false
     */
    @Test
    public void IterableFibonacciTest(){
        for (int i : new IterableFibonacci(18)) {
            System.out.print(i + " ");
        }
    }

    /**
     * 【泛型方法】
     * 是否拥有泛型方法，与其所在类是否泛型没关系
     * 指导原则：若泛型方法可取代整个类的泛型化，就该只使用泛型方法
     * static方法要使用泛型能力，就必须使其成为泛型方法
     * 编译器进行类型参数推断
     */
    @Test
    public void GenericMethodsTest(){
        /*public <T> void f(T x) {
            System.out.println(x.getClass().getName());
        }*/
    }

    /**
     * 【利用类型参数推断】
     * 编写工具类，包含static，专门用来创建各种常用的容器对象
     * 类型参数推断避免了重复的泛型参数列表
     */
    @Test
    public void NewTest(){
        Map<String, List<String>> sls = New.map();
        List<String> ls = New.list();
        LinkedList<String> lls = New.lList();
        Set<String> ss = New.set();
        Queue<String> qs = New.queue();
    }

    /**
     * 类型推断只对赋值操作有效
     */
    @Test
    public void LimitsOfInferenceTest(){
        /*f(java.util.Map<typeinfo.pets.Person,java.util.List<? extends typeinfo.pets.Pet>>)
        in LimitsOfInference cannot be appliedto(java.util.Map<java.lang.Object,java.lang.Object>)*/
        //f(New.map()); // Does not compile
    }

    /**
     * 可变参数与泛型方法
     */
    @Test
    public void GenericVarargsTest(){
        List<String> ls = makeList("A");
        //[A]
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        //[A, B, C]
        System.out.println(ls);
        //[, A, B, C, D, E, F, F, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z]
        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }

    /**
     * 一个通用的Generator：可为任何类构造一个Generator
     * 泛型化的create方法允许执行BasicGenerator.create(CountedObject.class)，而必须执行麻烦的new BasicGenerator<>(CountedObject.class)
     * 使用泛型方法创建Generator对象，大大减少我们的代码
     */
    @Test
    public void BasicGeneratorDemoTest(){
        new BasicGenerator<CountedObject>(CountedObject.class);
        Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }
    }

    /**
     *简化元组的使用
     */
    @Test
    public void TupleTest2Test(){
        //Tuple 改进前
        //TupleTest2 改进后

    }

    /**
     * 一个Set使用工具
     * 泛型方法，使用Set来表达数学中的关系式
     */
    @Test
    public void WatercolorSetsTest(){

    }

    /**
     * 打印出java.util包中各种Collection类和Map类之间的方法差异
     */
    @Test
    public void ContainerMethodDifferencesTest(){
        ContainerMethodDifferences containerMethodDifferences=new ContainerMethodDifferences();
        /*Collection: [add, addAll, clear, contains, containsAll, equals, forEach, hashCode, isEmpty, iterator, parallelStream, remove, removeAll, removeIf, retainAll, size, spliterator, stream, toArray]
        Interfaces in Collection: [Iterable]
        Set extends Collection, adds: []
        Interfaces in Set: [Collection]
        HashSet extends Set, adds: []
        Interfaces in HashSet: [Set, Cloneable, Serializable]
        LinkedHashSet extends HashSet, adds: []
        Interfaces in LinkedHashSet: [Set, Cloneable, Serializable]
        TreeSet extends Set, adds: [headSet, descendingIterator, descendingSet, pollLast, subSet, floor, tailSet, ceiling, last, lower, comparator, pollFirst, first, higher]
        Interfaces in TreeSet: [NavigableSet, Cloneable, Serializable]
        List extends Collection, adds: [replaceAll, get, indexOf, subList, set, sort, lastIndexOf, listIterator]
        Interfaces in List: [Collection]
        ArrayList extends List, adds: [trimToSize, ensureCapacity]
        Interfaces in ArrayList: [List, RandomAccess, Cloneable, Serializable]
        LinkedList extends List, adds: [offerFirst, poll, getLast, offer, getFirst, removeFirst, element, removeLastOccurrence, peekFirst, peekLast, push, pollFirst, removeFirstOccurrence, descendingIterator, pollLast, removeLast, pop, addLast, peek, offerLast, addFirst]
        Interfaces in LinkedList: [List, Deque, Cloneable, Serializable]
        Queue extends Collection, adds: [poll, peek, offer, element]
        Interfaces in Queue: [Collection]
        PriorityQueue extends Queue, adds: [comparator]
        Interfaces in PriorityQueue: [Serializable]
        Map: [clear, compute, computeIfAbsent, computeIfPresent, containsKey, containsValue, entrySet, equals, forEach, get, getOrDefault, hashCode, isEmpty, keySet, merge, put, putAll, putIfAbsent, remove, replace, replaceAll, size, values]
        HashMap extends Map, adds: []
        Interfaces in HashMap: [Map, Cloneable, Serializable]
        LinkedHashMap extends HashMap, adds: []
        Interfaces in LinkedHashMap: [Map]
        SortedMap extends Map, adds: [lastKey, subMap, comparator, firstKey, headMap, tailMap]
        Interfaces in SortedMap: [Map]
        TreeMap extends Map, adds: [descendingKeySet, navigableKeySet, higherEntry, higherKey, floorKey, subMap, ceilingKey, pollLastEntry, firstKey, lowerKey, headMap, tailMap, lowerEntry, ceilingEntry, descendingMap, pollFirstEntry, lastKey, firstEntry, floorEntry, comparator, lastEntry]
        Interfaces in TreeMap: [NavigableMap, Cloneable, Serializable]*/
    }

    /**
     * 【匿名内部类】
     * 泛型还可用于内部类&匿名内部类
     * 匿名内部类，实现Generator接口
     */
    @Test
    public void BankTellerTest(){
        BankTeller bankTeller=new BankTeller();
    }

    /**
     * 【构建复杂模型】
     * 构建的模型是一个零售店（包含走廊、货架和商品）
     */
    @Test
    public void StoreTest(){
        System.out.println(new Store(14, 5, 10));
    }

    /**
     *
     */
    @Test
    public void Test(){

    }
}
