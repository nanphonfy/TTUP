package generics;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.*;
import org.junit.Test;

import java.util.*;

import static generics.GenericVarargs.makeList;
import static net.mindview.util.Print.print;

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
     * 【擦除】
     */
    @Test
    public void ErasedTypeEquivalenceTest(){
        //可以ArrayList.class,不可以ArrayList<String>().class
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        //true，不同类型在行为方面肯定不同，但是程序认为他们是相同类型
        System.out.println(c1 == c2);
    }

    /**
     * getClass().getTypeParameters()将返回一个TypeVariable对象数组，表示有泛型声明所声明的类型参数
     * 在泛型代码内部，无法获得任何有关泛型参数类型的信息
     * java泛型用擦除实现，任何具体的类型信息都被擦除，唯一知道是使用一个对象
     * 理解擦除&如何处理（最大障碍）
     */
    @Test
    public void LostInformationTest(){
        LostInformation lostInformation = new LostInformation();

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

    /**
     * 因为擦除，java编译器无法映射HasF对象拥有f()。
     * 为调用f（），必须协助泛型类，给定泛型类的边界，以告知编译器只能接受遵循这个边界的类型。
     */
    @Test
    public void ManipulationTest(){
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();
    }

    /**
     * 边界<T extends HasF>声明T必须具有类型HasF或者从HasF导出的类型，安全的在obj上调用f（）
     * 类型参数的擦除：编译器实际会把类型参数替换为自己的擦除（T擦除到HasF）
     */
    @Test
    public void Manipulator2Test(){
        //<T extends HasF>
    }

    /**
     * 泛型没贡献任何好处，只需很容易的自己去执行擦除
     * 当希望代码能跨多个类工作时，使用泛型才有帮助
     */
    @Test
    public void Manipulator3Test(){
        Manipulator3 manipulator3=new Manipulator3(new HasF());
    }

    /**
     * 某个类返回T的方法，泛型就有帮助（能返回确切类型）
     * 必须查看所有代码，确定是否“足够复杂”到必须使用泛型
     */
    @Test
    public void ReturnGenericTypeTest(){
        ReturnGenericType returnGenericType=new ReturnGenericType(new HasF());
        returnGenericType.get();
    }

    /**
     * 擦除不是缺陷，而是java实现的一种折中。泛型类型只能在静态类型检查期间才出现，此后，都将被擦除，替换它为非泛型上界。
     * List<T>擦除为List
     * 普通类型变量擦除为Object
     * 【擦除的问题】
     * （使用非泛型的代码可不改变继续使用，直到重写为泛型）
     * Foo<T>:类型T总被替换，它只是一个object
     */
    @Test
    public void ErasureAndInheritanceTest(){
        ErasureAndInheritance erasureAndInheritance=new ErasureAndInheritance();
    }

    /**
     * 【边界处的动作】
     * 泛型可表示没有任何意义的事物
     * 元素视为Class<T>，擦除也意味着存储为Class，无任何参数
     * 实际创建数组，并未拥有元素所蕴含的类型信息
     */
    @Test
    public void ArrayMakerTest(){
        ArrayMaker arrayMaker=new ArrayMaker(String.class);
        //[null, null, null, null, null]
        System.out.println(Arrays.toString(arrayMaker.create(5)));
    }

    /**
     * 创建容器而不是数组，情况则不同
     */
    @Test
    public void ListMakerTest(){
        ListMaker listMaker=new ListMaker();
    }

    /**
     *
     */
    @Test
    public void FilledListMakerTest(){
        new FilledListMaker<String>();
    }

    /**
     * 对进入set的类型进行检查是不需要的（由编译器执行）
     * 而对get返回的值进行转型仍旧是需要的
     * 泛型所有动作的边界：传递进来的值激进型额外的编译期检查，对传出去的值进行转型
     * 有助于澄清对擦除的混淆（边界就是发生动作的地方）
     */
    @Test
    public void SimpleHolderTest(){
        SimpleHolder simpleHolder=new SimpleHolder();
    }

    /**
     * 擦除让泛型代码丢失了某些操作能力
     * 任何在运行时需指定确切类型的操作都无法工作
     */
    @Test
    public void ErasedTest(){
        new Erased<String>();
        //因为类型信息已经被擦除了
        /*if (arg instanceof T) {
        }          // Error
        T var = new T();                 // Error
        T[] array = new T[SIZE];         // Error
        T[] array = (T) new Object[SIZE]; // Unchecked warning*/
    }

    /**
     * 有时必须引入标签（显示传递类型的Class对象）来对擦除进行补偿
     * 编译器将确保类型标签可以匹配泛型参数
     */
    @Test
    public void ClassTypeCaptureTest(){
        new ClassTypeCapture<>(String.class);
    }

    /**
     * Erased.java无法创建一个new T()，部分原因是因为擦除，而另一部分原因是
     * 编译器不能验证T具有无参构造器
     * java的解决方案：传递一个工厂对象，并使用它来创建新实例
     */
    @Test
    public void InstantiateGenericTypeTest(){
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        print("ClassAsFactory<Employee> succeeded");
        try {
            /*ClassAsFactory<Integer> failed是因为Integer没有任何默认的构造器
            该错误不是在编译器捕获，所以这种写法不推荐*/
            ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        } catch (Exception e) {
            print("ClassAsFactory<Integer> failed");
        }
    }

    /**
     * 建议使用显示的工厂并限制其类型，使得只能接受实现这个工厂的类
     * 只传递Class<T>的一种变体
     */
    @Test
    public void FactoryConstraintTest(){
        new Foo2<>(new IntegerFactory());
        new Foo2<>(new Widget.Factory());
    }

    /**
     * 模板方法设计模式
     * get是模板方法
     * create是在子类中定义的、用来产生子类类型的对象
     */
    @Test
    public void CreatorGenericTest(){
        CreatorGeneric creatorGeneric=new CreatorGeneric();
    }

    /**
     * 【泛型数组】
     * Erased.java不能创建泛型数组，
     * 解决方案：想要建泛型数组的地方都使用ArrayList
     */
    @Test
    public void ListOfGenericsTest(){
        ListOfGenerics listOfGenerics=new ListOfGenerics();
    }

    /**
     * 创建泛型类型的数组（eg.ArrayList内部使用的是数组）
     */
    @Test
    public void ArrayOfGenericReferenceTest(){
        ArrayOfGenericReference arrayOfGenericReference=new ArrayOfGenericReference();
    }

    /**
     * 编译器不能创建确切类型的数组（包括类型参数）
     * 无论持有何种类型，都具有相同结构，创建一个Objet数组
     * 并将其转型为所希望的数组类型，将产生异常
     */
    @Test
    public void ArrayOfGenericTest(){
        ArrayOfGeneric arrayOfGeneric=new ArrayOfGeneric();
    }

    /**
     * 数组将跟踪他们的实际类型（在数组被创建时确定）
     * 成功创建泛型数组的唯一方式:
     * 就是创建一个被擦除类型的新数组,然后对其转型
     */
    @Test
    public void GenericArrayTest(){
        GenericArray<Integer> gai = new GenericArray<>(10);
        // java.lang.ClassCastException
        Integer[] ia = gai.rep();
        // This is OK:
        Object[] oa = gai.rep();
    }

    /**
     * 我们不能声明T[] array = new T[sz]
     * 因此得创建一个对象数组，再将其转型
     * 因为有擦除，数组运行时类型就只能是Object[]，
     * 如果我们立即将其转型为T[]，那么编译期该数组的实际类型将丢失
     */
    @Test
    public void GenericArray2Test(){
        /**
         * 在内部将数组当做Object[]而不是T[]处理的优势
         不太可能忘记数组运行时类型而意外引入缺陷
         */
        GenericArray2 genericArray2=new GenericArray2(2);
    }

    /**
     * 对于新代码，应该传递一个类型标记
     * 类型标记Class<T>被传递到构造器中，以便从擦除恢复
     * 该数组运行时类是确切类型T[]
     */
    @Test
    public void GenericArrayWithTypeTokenTest(){
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        // This now works:
        Integer[] ia = gai.rep();
    }

    /**
     * 【边界】
     * 用于泛型的参数类型上设置限制条件
     * 要理解extends在泛型边界上下文环境中和在普通环境下所具有的意义完全不同
     * 下面展示了边界的基本要素
     * BasicBounds看上去包含可以通过继承消除的冗余
     */
    @Test
    public void BasicBoundsTest(){
        BasicBounds basicBounds=new BasicBounds();
    }

    /**
     * 如何在继承的每个层次上添加边界限制
     */
    @Test
    public void InheritBoundsTest(){
        InheritBounds inheritBounds=new InheritBounds();
    }

    /**
     * 该例有更多层次的示例
     * 通配符被限制为单一边界
     */
    @Test
    public void EpicBattleTest(){
        EpicBattle epicBattle=new EpicBattle();
    }

    /**
     *
     */
    @Test
    public void Test(){

    }
}
