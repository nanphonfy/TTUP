package innerclasses;

import innerclasses.controller.Controller;
import innerclasses.controller.Event;
import org.junit.Test;

import static innerclasses.AnonymousConstructor.getBase;
import static innerclasses.MultiImplementation.takesD;
import static innerclasses.MultiImplementation.takesE;
import static innerclasses.MultiInterfaces.takesA;
import static innerclasses.MultiInterfaces.takesB;
import static innerclasses.Parcel11.contents;
import static innerclasses.Parcel11.destination;
import static net.mindview.util.Print.print;

/**
 * 内部类：可将一个类的定义放在另一个类的定义内部
 * 内部类与组合是完全不同的概念
 * 它像一种代码隐藏机制：将类置于其他类的内部；它了解外部类，能与之通信；用它能使代码更优雅清晰
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/11
 */
public class T10 {
    /**
     *ship方法内使用内部类与普通类没有什么不同
     */
    @Test
    public void Parcel1Test(){
        Parcel1 p = new Parcel1();
        p.ship("Tasmania");
    }

    /**
     * 外部类有一个方法，返回一个指向内部类的引用,eg.contents、to方法
     */
    @Test
    public void Parcel2Test(){
        Parcel2 p = new Parcel2();
        p.ship("Tasmania");

        //从外部类的非静态方法创建某个内部类对象
        // Defining references to inner classes:
        p = new Parcel2();
        Parcel2.Contents c = p.contents();
        Parcel2.Destination d = p.to("Borneo");
    }

    /**
     * 链接到外部类
     * 内部类的对象能访问外围对象的所有成员，不需任何特殊条件
     * 内部类可访问其外围类的方法和字段，就像自己拥有他们一样
     */
    @Test
    public void SequenceTest(){
        /*当某外围对象创建了一个内部类对象时（它会秘密捕获一个指向那个外围类对象的应用），
        然后用那个引用选择外围类的成员,编译器帮我们处理所有细节*/
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            sequence.add(Integer.toString(i));
        }
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }

    /**
     * 使用.this和.new
     * 若在内部类需生成对外部类对象的引用，可使用 外部类.this
     * 产生的引用能自动具有正确类型，在编译期就被知晓并受检查
     */
    @Test
    public void DotThisTest(){
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }

    /**
     * 直接创建内部类对象，不能引用外部类名字，
     * 而必须使用用外部类对象创建某个内部类对象
     * 在拥有外部类对象之前是不能创建内部类对象的（因为内部类会秘密连接到创建它的外部类对象上）
     */
    @Test
    public void DotNewTest(){
        DotNew dn = new DotNew();
        DotNew.Inner dni = dn.new Inner();
        //若创建的是嵌套类（静态内部类），就不需对外部类对象的引用
    }

    /**
     * .new应用于Parcel3
     */

    @Test
    public void Parcel3Test(){
        Parcel3 p = new Parcel3();
        // Must use instance of outer class
        // to create an instance of the inner class:
        Parcel3.Contents c = p.new Contents();
        Parcel3.Destination d = p.new Destination("Tasmania");
    }

    /**
     * 内部类与向上转型
     * 将内部类向上转型为基类，尤其是转型为接口时，内部类就有了用武之地，所得到的只是指向基类或接口的引用
     * private内部类可完全阻止任何依赖于类型的编码，并完全隐藏实现细节
     */
    @Test
    public void Parcel4(){
        Parcel4 p = new Parcel4();
        Destination d = p.destination("kangkang");
        Contents c = p.contents();

        Parcel4.PDestination ppd = p.new PDestination("kk");
        //'innerclasses.Parcel4.PContents' has private access in 'innerclasses.Parcel4
        //Parcel4.PContents ppc = p.new PContents();
    }

    /**
     * 在方法和作用域内的内部类
     * 可在一个方法或任意作用域内定义内部类
     * 意义：①如上，实现某类型接口，创建并返回对其引用；②要解决的复杂问题，需创建一个类来辅助，但又不希望这个类公共可用。
     */
    @Test
    public void Parcel5Test(){
        Parcel5 p = new Parcel5();
        Destination d = p.destination("Tasmania");
    }

    /**
     * 在任意的作用域内嵌入一个内部类
     */
    @Test
    public void Parcel6Test(){
        Parcel6 p = new Parcel6();
        p.track();
    }

    /**
     * 匿名内部类
     * 建一个继承或实现XXX的匿名类的对象
     */
    @Test
    public void Parcel7Test(){
        Parcel7 p = new Parcel7();
        Contents c = p.contents();
    }

    /**
     * 以上匿名内部类等价写法
     */
    @Test
    public void Parcel7bTest(){
        Parcel7b p = new Parcel7b();
        Contents c = p.contents();
    }

    /**
     * 匿名内部类使用有参构造函数
     */
    @Test
    public void Parcel8Test(){
        Parcel8 p = new Parcel8();
        Wrapping w = p.wrapping(10);
    }

    /**
     * 在匿名类中定义字段，还能对其初始化
     * 若定义一个匿名内部类，并希望使用外部定义的对象
     * 编译器会要求参数引用为final
     */
    @Test
    public void Parcel9Test(){
        Parcel9 p = new Parcel9();
        Destination d = p.destination("Tasmania");
    }

    /**
     * 通过实例 初始化
     * 达到对匿名内部类做类似于构造器的行为
     * 不要求变量i一定是final，因为i不会直接被匿名类内部使用
     */
    @Test
    public void AnonymousConstructorTest(){
        Base base = getBase(47);
        base.f();
    }

    /**
     * 通过实例 初始化
     * 达到对匿名内部类做类似于构造器的行为
     * 注意参数必须是final，因为他们在匿名类内部使用
     * 对于匿名内部类而言，实例初始化的实际效果就是构造器
     */
    @Test
    public void Parcel10Test(){
        Parcel10 p = new Parcel10();
        Destination d = p.destination("Tasmania", 101.395F);
    }

    /**
     * 匿名内部类与正规继承相比受限，因为匿名内部类既可以扩展类，也可以实现接口（只能实现一个接口），但不能两者兼备
     */

    /**
     * 再访工厂方法
     * 用匿名内部类 美妙地实现工厂方法
     */
    @Test
    public void FactoriesTest(){
        Factories.serviceConsumer(Implementation1.factory);
        // Implementations are completely interchangeable:
        Factories.serviceConsumer(Implementation2.factory);
    }

    /**
     * 用匿名内部类，改造Games
     * 优先使用类而不是接口
     */
    @Test
    public void GamesTest(){
        Games.playGame(Checkers.factory);
        Games.playGame(Chess.factory);
    }

    /**
     * 嵌套类
     * 若不需内部类对象与外围对象联系，可将内部类声明为static——嵌套类
     * 普通内部类对象隐式保持了指向外围类对象的引用，
     * 而嵌套类：①要创建嵌套类对象，并不需其外围类对象；②不能从嵌套类对象中访问非静态的外围对象。
     * 普通内部类不能有static数据和字段，也不能包含嵌套类，
     * 但嵌套类可以包含所有这些东西
     */
    @Test
    public void Parcel11Test(){
        // 没有任何Parcel11对象是必须的，
        // 嵌套类没有this引用（指向外部类），这使得它类似一个static方法
        Contents c = contents();
        Destination d = destination("Tasmania");
    }

    /**
     * 接口内部的类
     * 一般，不能在接口内部放置任何代码，但嵌套类可以作为接口的一部分
     * 放到接口中的任何类都自动地是public和static
     * 如果要创建某些公共代码，使他们可以被某个接口的所有不同实现所公用，那使用接口内部的嵌套类会很方便
     */
    @Test
    public void ClassInInterfaceTest(){
        ClassInInterface.Test.main(new String[0]);
    }

    /**
     * 使用嵌套类放置测试代码
     */
    @Test
    public void TestBedTest(){
        TestBed.Tester.main(new String[0]);
    }

    /**
     * 从多层嵌套类中访问外部类的成员
     * 如何从不同类里创建多层嵌套的内部类对象
     * .new语法能产生正确的作用域，所以不必在调用构造器时限定类名
     */
    @Test
    public void MultiNestingAccessTest(){
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }

    /**
     * 为什么需要内部类
     * 内部类继承自某个类或实现某个接口，它可操作其外围类对象
     * 每个内部类都能独立地继承自一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没影响。
     * 内部类提供可继承多个具体的或者抽象的类的能力，使一些设计与编程问题变得容易解决。它使得多重继承的解决方案变得完整。
     * 接口解决了部分问题，而内部类有效实现了"多重继承"
     */

    /**
     * 一个类中实现两个接口：①使用单一类；②使用过内部类
     */
    @Test
    public void MultiInterfacesTest(){
        X x = new X();
        Y y = new Y();
        takesA(x);
        takesA(y);
        takesB(x);
        takesB(y.makeB());
    }

    /**
     * 若拥有的是抽象的类或具体的类，而不是接口，那就只能使用内部类才能实现多重继承
     */
    @Test
    public void MultiImplementationTest(){
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }

    /**
     * 若无需解决"多重继承"问题，那自然可用别的编码方式，而无需用内部类，
     但使用内部类还可获得其他一些特性：
     ①内部类可有多个实例（都有自己的状态信息并与外围类对象的信息相互独立）；
     ②在单个外围类，可让多个内部类以不同方式实现同一接口或继承同一类；
     ③创建内部类对象的时刻并不依赖于外围类的创建；
     ④内部类是一个独立的实体。
     eg.若Sequence类不使用内部类，就必须声明它是一个Selector，
     对于特定的Sequence只能有一个Selector，然而使用内部类很容易就能拥有另一个方法reverseSelector，只有内部类才有这种灵活性。
     */

    /**
     * 闭包与回调
     * java提供类似指针的机制以允许回调，通过回调，对象能够携带一些信息。
     * 通过内部类提供闭包，比指针更灵活、安全
     * 回调的价值在于它的灵活性——可在运行时动态决定需要调用什么方法（GUI到处都用到回调）
     */
    @Test
    public void CallbacksTest(){
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        //Other operation
        //1
        MyIncrement.f(c2);

        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        //1
        //2
        caller1.go();
        caller1.go();
        /*Other operation
        2
        Other operation
        3*/
        caller2.go();
        caller2.go();
    }

    /**
     * 【内部类与控制框架】
     * 控制框架主要用来响应事件的需求（事件驱动），
     * java的图形用户接口(GUI)，几乎完全是事件驱动的系统（使用了大量内部类）。
     */
    @Test
    public void EventControllerTest(){
        Controller controller=new Controller();
        /**
         * 内部类允许：①表示解决问题所必须的各种不同的action；②很容易访问外围类的任意成员，避免实现变得笨拙
         * 此处为匿名内部类
         */
        controller.addEvent(new Event(70000) {
            @Override public void action() {
                System.out.println("take easy!!!");
            }
        });
        controller.run();
    }

    /**
     * 控制温室的运作：
     * 控制灯光、水、温度调节器的开关、响铃和重启系统，每个行为都是完全不同的。
     * 使用内部类，可在单一的类里产生对同一个基类Event的多种版本，并在action中编写控制代码
     * 该例使读者更了解内部类的价值，特别是在控制框架中使用内部类的时候
     */
    @Test
    public void GreenhouseControllerTest(){
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = { gc.new ThermostatNight(0), gc.new LightOn(200), gc.new LightOff(400), gc.new WaterOn(600),gc.new WaterOff(800), gc.new ThermostatDay(1400) };
        gc.addEvent(gc.new Restart(2000, eventList));
        gc.run();
    }

    /**
     * 【内部类的继承】
     * 内部类的构造器必须连接到指向其外围类对象的引用，所以在继承内部类时，那个指向外围类对象的"秘密"的引用必须被初始化
     * 要解决这个问题，必须使用特殊的语法明确说明他们之间的关联
     */
    @Test
    public void InheritInnerTest(){
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }

    /**
     * 【内部类的覆盖】
     * 创建了一个内部类，然后继承其外围类并重新定义此内部类，
     * 但是"覆盖"内部类就好像它是外围类的一个方法，其实不起作用。
     */
    @Test
    public void BigEggTest(){
        //New Egg()
        //Egg.Yolk()
        new BigEgg();
    }

    /**
     * 明确指出继承哪个内部类是可以的
     */
    @Test
    public void BigEgg2Test(){
        /*Egg2.Yolk()
        New Egg2()
        Egg2.Yolk()
        BigEgg2.Yolk()*/
        Egg2 e2 = new BigEgg2();
        //BigEgg2.Yolk.f()
        e2.g();
    }

    /**
     * 【局部内部类】
     * 局部内部类不能有访问说明符，它可以访问当前代码块内的常量+此外围类的所有成员。
     * 局部内部类VS匿名内部类
     * 局部内部类的名字在方法外不可见，仍要使用它的原因是：
     * 需要一个已命名的构造器，或需重载构造器（需要不止一个该内部类）
     * 而匿名内部类只能用于实例初始化。
     */
    @Test
    public void LocalInnerClassTest(){
        LocalInnerClass lic = new LocalInnerClass();
        Counter c1 = lic.getCounter("Local inner "),
                c2 = lic.getCounter2("Anonymous inner ");
        for (int i = 0; i < 5; i++) {
            print(c1.next());
        }
        for (int i = 0; i < 5; i++) {
            print(c2.next());
        }
    }

    /**
     *【内部类标识符】
     * 类文件的命名规则：外围类名字+'$'+内部类名字
     */
    @Test
    public void LocalInnerClass$Test(){
        /**
         * 若内部类是匿名的，编译器会简单产生一个数字做为其标识符
         * 若内部类是嵌套在别的内部类中，只需直接将他们的名字……
         */
        //Counter.class
        //LocalInnerClass$1.class
        //LocalInnerClass$1LocalCounter.class
        //LocalInnerClass.class
    }

    /**
     *
     */
    @Test
    public void Test(){

    }
}
