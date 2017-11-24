package generics;

import net.mindview.util.ThreeTuple;
import net.mindview.util.TwoTuple;

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
     *
     */
    public void Test(){

    }
}
