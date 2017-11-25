package generics;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.Generator;
import net.mindview.util.New;
import net.mindview.util.ThreeTuple;
import net.mindview.util.TwoTuple;

import java.util.*;

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
    public void Holder1Test(){

    }

    /**
     *让类持有Object类型的对象
     * Holder2可存储任何类型的对象
     */
    public void Holder2Test(){

    }

    /**
     * 泛型：用来指定容器要持有什么类型的对象，且由编译器来保证类型的正确性
     * 创建对象时，必须指明想持有什么类型的对象，将其置于尖括号内
     */
    public void Holder3Test(){

    }

    /**
     * 【一个元组类库】
     * 仅一个方法调用就能返回多个对象
     * 元组（可具有任意长度，元组中的对象可是任意类型）：将一组对象直接打包存储在单一对象中
     * 2维元组（持有两个对象）
     */
    public void TupleTest(){
        TwoTuple twoTuple=new TwoTuple("2",1);
        //使用继承，实现长度更长的元组
        ThreeTuple threeTuple=new ThreeTuple(1.2,false,"kk");
    }

    /**
     * 使用元组：定义一个长度适合的元组，将其作为返回值
     * final声明能保护public元素，被构造出来后，就不能再被赋值
     */
    public void TupleTestTest(){

    }

    /**
     * 【一个堆栈类】
     * 传统的下推堆栈
     * 不用LinkeList，实现自己的内部链式存储机制
     * 使用了末端哨兵（在构造LinkedStack时创建），判断堆栈何时为空，然后每次调用push，就会创建一个Node<T>对象，并将其链接到前一个Node<T>对象
     */
    public void LinkedStackTest(){

    }

    /**
     * 持有特定类型对象的列表，可应用于各种类型的对象的工具
     */
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
    public void FibonacciTest(){

    }

    /**
     * 有多种方法可实现适配器（eg.通过继承创建适配器类）
     * 要在类的循环语句使用IterableFibonacci，必须向IterableFibonacci构造器提供一个边界值，才能知道haseNext何时返回false
     */
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
    public void NewTest(){
        Map<String, List<String>> sls = New.map();
        List<String> ls = New.list();
        LinkedList<String> lls = New.lList();
        Set<String> ss = New.set();
        Queue<String> qs = New.queue();
    }

    /**
     *
     */
    public void Test(){

    }
}
