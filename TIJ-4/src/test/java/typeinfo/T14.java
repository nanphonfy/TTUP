package typeinfo;

import net.mindview.util.Null;
import org.junit.Test;
import typeinfo.pets.LiteralPetCreator;
import typeinfo.pets.Pets;
import typeinfo.toys.GenericToyTest;
import typeinfo.toys.ToyTest;

/**
 * 运行时类型信息使得可在程序运行时发现和使用类型信息
 * java在运行时识别对象和类信息：①传统RTTI（假定在编译时已知道所有类型）；
 * ②反射，运行时发现和使用类信息。
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/12/10
 */
public class T14 {
    /**
     * 【为什么需要RTTI】
     * 基类位于顶部，派生类向下扩展
     * RTTI，在运行时，识别一个对象的类型
     */
    @Test
    public void ShapesTest() {
        Shapes shapes=new Shapes();
    }

    /**
     * 【Class对象】
     * 理解RTTI在java中的工作原理：
     * 必须知道类型信息在运行时是如何表示的
     * java使用Class对象（被保存在一个同名的.class文件中）执行其RTTI
     * 为生成这个类的对象，JVM将使用“类加载器”（包含一条类加载器链，但只有一个原生类加载器，是JVM实现的一部分）
     * 所有类都在第一次使用时动态加载到JVM，java程序在运行前未被完全加载，各个部分是在必须时才加载。
     * 类加载器：①检查这个类的Class对象是否已加载；
     * ②未加载，类加载器会根据类名查找.class文件；
     * ③字节码被加载时，接受验证，确保其没被破坏。
     * 一旦某个类的Class对象被载入内存，就可被用来创建这个类的所有对象。
     */
    @Test
    public void SweetShopTest() {
        /**
         * Class对象近在需要时才被加载，static初始化是在类加载时进行
         Class.forName()是Class类的一个static成员，Class对象就和其他对象一样，可获取并操作它的引用（类加载器的工作）
         forName()是取得Class对象引用的方法
         想在运行时使用类型信息，就必须先获得Class对象的引用
         */
        SweetShop sweetShop=new SweetShop();
    }

    /**
     * 若已经有类型对象，就可调用getClass来获取Class引用
     * Class的newInstance() ：实现“虚拟构造器”的一种途径
     */
    @Test
    public void ToyTestTest() {
        ToyTest toyTest=new ToyTest();
    }

    /**
     * 【类字面常量】
     * XX.class（更简单、安全、高效），可应用普通类、接口、数组以及基本数据类型
     * 使用.class对象引用时，不会自动地初始化该Class对象
     * ①加载（类加载器将查找字节码并创建一个Class对象）
     * ②链接（验证类中的字节码，为静态域分配存储空间，解析该类创建的对其他类的所有引用）
     * ③初始化（对其初始化，执行静态初始化器和静态初始化块）
     * 初始化被延迟到对静态方法（构造器隐式是静态的）或非数静态域进行首次引用时才执行
     */
    @Test
    public void ClassInitializationTest() {
        /*初始化有效实现了尽可能的惰性
        仅适用.class获得类引用，不会引发初始化
        Class.forName立即进行了初始化*/
        ClassInitialization classInitialization=new ClassInitialization();
    }

    /**
     * 【泛化的Class引用】
     * Class引用表示的就是它所指向的对象的确切类型，而该对象便是Class类的一个对象
     * 以下用到了泛型语法，通过泛型，可让编译器强制执行额外的类型检查
     */
    @Test
    public void GenericClassReferencesTest() {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // Same thing
        intClass = double.class;
        //genericIntClass = double.class; // Illegal
    }

    /**
     * 为了在使用泛化的Class引用时放松限制，可使用通配符（?，表示任何事物）
     * Class<?>表示并非碰巧或疏忽，而是使用了一个非具体的类引用
     */
    @Test
    public void WildcardClassReferencesTest() {
        Class<?> intClass = int.class;
        intClass = double.class;
    }

    /**
     * 将通配符与extends关键字相结合，创建一个范围
     */
    @Test
    public void BoundedClassReferencesTest() {
        Class<? extends Number> bounded = int.class;
        bounded = double.class;
        bounded = Number.class;
        // Or anything else derived from Number.
    }

    /**
     * Class引用添加泛型：为了提供编译期类型检查，不用等到运行时才发现错误
     * 以下使用泛型类语法
     * 存储一个类的引用，填充List的对象使用newInstance()方法
     * newInstance()方法必须假设任何与它工作的类型都有默认构造器（无参）
     */
    @Test
    public void FilledListTest() {
        FilledList<CountedInteger> fl = new FilledList<>(CountedInteger.class);
        System.out.println(fl.create(15));
    }

    /**
     * 泛型用于Class对象，newInstance()将返回该对象的确切类型，而不仅仅只是Object
     */
    @Test
    public void GenericToyTestTest() {
        GenericToyTest genericToyTest=new GenericToyTest();
    }

    /**
     * 【新的转型语法】
     * Class引用的转型语法——cast方法
     * 对于无法使用普通转型的情况非常有用
     * 若存储了Class引用，可通过这个引用转型
     */
    @Test
    public void ClassCastsTest() {
        ClassCasts classCasts=new ClassCasts();
    }

    /**
     * 【类型转换前先做检查】
     * RTTI形式：
     * ①传统的类型转换，RTTI确保类型转换正确性（ClassCastException）
     * ②代表对象类型的Class对象，获取运行时信息
     * ③instanceof，返回布尔值，告诉我们对象是不是某个特定类型实例
     * 下面是继承自Individual的类继承体系
     */
    @Test
    public void PetCountTest() {
        /**
         * 可随机创建不同类型的宠物，还可创建宠物数组和List
         * 为使工具能够适用多种不同实现，定义为抽象类
         * 【PetCreator】
         */

        /**
         * ForNameCreator：用Class.forName
         *
         * PetCount：对Pet计数
         * instanceof的限制：只可将其与命名类型比较，不能与Class对象做比较
         有一个替代方案，稍后演示（当程序有许多instanceof，说明设计有瑕疵）
         */
        PetCount petCount = new PetCount();
    }

    /**
     * 【使用类字面常量】
     * 重新实现PetCount，更清晰
     */
    @Test
    public void LiteralPetCreatorTest() {
        LiteralPetCreator literalPetCreator=new LiteralPetCreator();
    }

    /**
     * 【使用类字面常量】
     * 在typeinfo.pets类库有两种PetCreator实现，为将第二种实现做为默认实现，可创建一个使用LiteralPetCreator的外观
     */
    @Test
    public void PetsTest() {
        Pets pets = new Pets();
    }

    /**
     * 【使用类字面常量】
     * 在typeinfo.pets类库有两种PetCreator实现，为将第二种实现做为默认实现，可创建一个使用LiteralPetCreator的外观
     */
    @Test
    public void PetCount2Test() {
        PetCount2 petCount2 = new PetCount2();
    }

    /**
     * 【动态instanceof】
     * Class.isInstance方法提供了一种动态测试对象的途径，所有instanceof语句都可被替换掉。
     * isInstance方法使我们不再需要instanceof表达式。
     * 也意味着添加新类型Pet，只需简单改变LiteralPetCreator数组，而不用改动程序
     * （使用instanceof时是不可能的）。
     */
    @Test
    public void PetCount3Test() {
        PetCount3 petCount3 = new PetCount3();
    }

    /**
     * 【递归计数】
     * 在PetCount3中的Map预加载所有不同的Pet类。使用Class.isAssignableFrom，并创建一个不局限于对Pet计数的通用工具。
     */
    @Test
    public void TypeCounterTest() {
        //TypeCounter typeCounter=new TypeCounter();
        PetCount4 petCount4=new PetCount4();
    }

    /**
     * 【注册工厂】
     * 生成Pet继承机构中的对象的问题：添加新Pet类型时，必须添加为LiteralPetCreator中的项。
     * 考虑在每个子类添加静态初始化器，使得将新类添加到List中。然而生成器在其列表中不包含该类，因此永远不能创建这个类的对象，也不能被加载并置于这个列表。
     * 使用工厂方法设计模式，将对象创建工作交给类自己去完成，工厂方法可被多态调用，从而创建恰当类型的对象。
     * 泛型T使得create可在每种Factory实现中返回不同的类型，充分利用了协变返回类型。
     */
    @Test
    public void RegisteredFactoriesTest() {
        RegisteredFactories registeredFactories = new RegisteredFactories();
    }

    /**
     * 【instanceof与Class的等价性】
     * 在查询类型信息时，以instanceof的形式与直接比较Class对象有一个很重要的差别
     * instanceof和isInstance生成的结果完全一样，equals和==也一样，但这两组测试得出的结论却不同。
     * instanceof保持了类型的概念（是该类或是该类的派生类）
     * Class用==比较，就没有考虑继承。
     */
    @Test
    public void FamilyVsExactTypeTest() {
        FamilyVsExactType familyVsExactType = new FamilyVsExactType();
    }

    /**
     * 【反射：运行时的类信息】
     * 假设获取一个指向某个不在我们程序空间的对象引用，编译器没法获知该对象所属类。
     * eg.从磁盘文件或网络连接获取一串字节，被告知代表一个类。
     * 置身于更大规模的编程世界：
     * ①基于构建的编程，可视化编程方法，反射提供了一种机制——检查可用的方法并返回方法名。
     * java通过JavaBeans提供了基于构件的编程架构。
     * ②运行时获取类信息的另一动机：希望提供在跨网络的远程平台上创建和运行对象的能力。
     * 这被称为远程方法调用（RMI），允许一个java程序将对象分布到多台机器上。
     * eg.大计算量的任务划分多计算单元到空闲机器、处理特定类型任务的代码到特定机器、分布式计算也支持适用于执行特殊任务的专用硬件……
     * Class类和java.lang.reflect类库一起对反射概念提供了支持，包含Field、Method、Constructor类，
     * 这些类型的对象由JVM在运行时创建，用以表示未知类里对应的成员。
     * 【认识反射机制并无神奇之处】通过反射与一个未知类型的对象打交道时，JVM只简单检查这个对象，看它属于哪个特定的类。在用它做其他事情前，必须先加载那个类的Class对象。
     * 因此，那个类的.class文件对于JVM来说必须是可获取的：①本地机器；②网络。
     * 【RTTI和反射的区别】对RTTI（Run-Time Type Identification）来说，编译器在编译时打开和检查.class文件。
     * 对反射机制来说，.class文件在编译时是不可获取的，所以是在运行时打开和检查.class文件。
     */

    /**
     * 【类方法提取器】
     * 通常不需直接使用反射工具，但在需要创建更动态的代码时会很有用。
     * eg.类方法提取器，浏览了类定义的源代码或是其JDK文档。反射机制能够编写自动展示完整接口的简单工具。
     *
     * 若不记得一个类是否有某方法或不知该类能做啥，该工具能节省很多时间
     */
    @Test
    public void ShowMethodsTest() {
        ShowMethods showMethods = new ShowMethods();
    }

    /**
     * 【代理】
     * 代理是基本设计模式之一（充当中间人，涉及与“实际”对象的通信）
     * 展示代理结构的简单实例：
     * consumer()接受Interface。无法做到正在获取的是RealObject或SimpleProxy。
     * 但SimpleProxy已被插入到客户端和RealObject之间，因此会执行并调用RealObject上相同的方法。
     */
    @Test
    public void SimpleProxyDemoTest() {
        /**
         * 任何时刻，只要想额外从实际对象中分离到不同地方，特别是想能够容易做出修改，代理就显得很有用。
         eg.跟踪RealObject中方法的调用或希望度量这些调用的开销。
         肯定不希望将其合并到应用中的代码，而代理可容易添加或移除他们。
         */
        SimpleProxyDemo simpleProxyDemo = new SimpleProxyDemo();
    }

    /**
     * 【动态代理】
     * 动态代理比代理思想更前卫，可动态创建代理并动态处理对所代理方法的调用。
     * 动态代理所做的所有调用都会被重定向到单一调用处理器上，它的工作是揭示调用的类型并确定相应的对策。
     * 用动态代理重写SimpleProxyDemo
     */
    @Test
    public void SimpleDynamicProxyTest() {
        /**
         * 调用静态方法Proxy.newProxyInstance(）可创建动态代理，需要一个类加载器&一个该代理实现的接口列表（不是类或抽象类），
         * 以及InvocationHandler接口的一个实现。动态代理可将所有调用重定向到调用处理器。
         向处理器的构造器传递一个实际对象的引用，执行中介任务时，请求转发。
         invoke传递进代理对象，对接口的调用将重定向为对代理的调用。
         */
        SimpleDynamicProxy simpleDynamicProxy = new SimpleDynamicProxy();
    }

    /**
     * 【动态代理】
     * 通常，我们会执行被代理的操作，使用method.invoke()将请求转发给被代理对象，并传入必须的参数。
     * 看起来受限，但可通过传递其他参数，过滤某些方法调用：
     * 这里，我们只查看了方法名，还可查看方法签名的其他方面甚至搜索参数值。
     * 动态代理并非为日常使用工具，但它可很好解决某些类型的问题。
     */
    @Test
    public void SelectingMethodsTest() {
        SelectingMethods selectingMethods=new SelectingMethods();
    }

    /**
     * 【空对象】
     * 使用内置的null每次使用其引用都必须检查其是否为null，显得枯燥。
     * 这时引入空对象思想将很有用（返回实际上不存在的“真实”对象的值，而不必浪费精力去检查null）。
     * 即使空对象可响应“实际”对象可以响应的所有消息，也仍需测试是否为空（创建一个标记接口）。
     */
    @Test
    public void NullPersonTest() {
        Null nullTest;
        /***
         * 这使得instanceof可探测空对象，不需要在所有类都添加isNull方法。
         通常空对象都是单例的，因此用静态final实例创建。
         */
        Person.NullPerson nullPerson;
    }

    /**
     * 【空对象】
     * 将Person空对象放在每个Position上。
     * 有了Position就不需创建空对象了，因为Person.NULL的存在就表示这是一个空Position。
     */
    @Test
    public void PositionTest() {
        Position position = new Position("test");
    }

    /**
     * 【空对象】
     * Staff类可在填充职位时查询空对象
     * 注意：某些地方仍需测试空对象，与检查是否为null没差异，
     * 但在其他地方（eg.本例的toString）就不需额外测试（可直接假设所有对象都有效）
     */
    @Test
    public void StaffTest() {
        Staff staff = new Staff();
    }

    /**
     * 【空对象】
     * 若用接口取代具体类，可使用动态代理来自动创建空对象。
     * 假设Robot接口，定义一个名字、一个模型和一个描述Robot行为能力的List<Operation>，Operation包含一个描述和一个命令
     * 可通过operations来访问Robot的服务
     * 创建一个扫雪Robot
     */
    @Test
    public void SnowRemovalRobotTest() {
        SnowRemovalRobot snowRemovalRobot = new SnowRemovalRobot("test");
    }

    /**
     * 【动态代理&空对象】
     * 假设有多种不同类型的Robot，相对没中过类型都创建一个空对象，可通过动态代理捕获.
     * 无论何时，需一个空Robot对象，只需调用newNullRobot，并传递需要代理的Robot类型。
     * 代理会满足Robot和Null接口的需求，并提供所代理的类型的确切名字。
     */
    @Test
    public void NullRobotTest() {
        NullRobot nullRobot = new NullRobot();
    }

    /**
     * 【模拟对象和桩】
     空对象的了逻辑变体是模拟对象和桩。但模拟对象和桩都只是假扮可传递实际信息的存活对象，而空对象是一种更加智能化的替代物。
     模拟对象是轻量级和自测试的，桩通常是重量级的，经常在测试间被复用。
     */

    /**
     * 【接口与类型信息】
     * interface重要目标：隔离构件，降低耦合性。
     * 通过接口可实现该目标，而通过类型信息，这种耦合性会被传播出去。
     * 接口并非是对解耦的无懈可击的保障。
     */
    @Test
    public void InterfaceViolationTest() {
        //A接口
        //实现A接口，可看到代码围绕实际实现类型潜行
        //通过RTTI，a被当做B实现，通过将其转型为B，可调用不在A中的方法
        InterfaceViolation interfaceViolation = new InterfaceViolation();
    }

    /**
     * 【接口与类型信息】
     * 接口并非是对解耦的无懈可击的保障
     * 最简单的解决方案：对实现使用包访问权限，包外部的客户端看不到它
     * 唯一的public类HiddenC可在其他包调用，产生A接口类型的对象（即使返回的是C类型）。
     * 包外不能命名C，若试图向下转型C，将被禁止。
     *
     *
     * 而通过反射，仍旧可以调用所有方法，甚至private。若知道方法名，就可在其对象Method对象调用setAccessible(true)，就像在callHiddenMethod中一样。
     * 只发布编译后的代码，也并不解决问题，因为只需运行javap（反编译器）即可突破限制。
     * javap -private C
     * 因此任何人都可获取最私有的方法的名字和签名，并调用。
     */
    @Test
    public void HiddenImplementationTest() {
        HiddenImplementation hiddenImplementation = new HiddenImplementation();
    }

    /**
     * 若将接口实现为一个私有内部类，对反射仍旧无任何隐藏
     */
    @Test
    public void InnerImplementationTest() {
        InnerImplementation innerImplementation = new InnerImplementation();
    }

    /**
     * 若将接口实现为一个匿名类，对反射仍旧无任何隐藏
     */
    @Test
    public void AnonymousImplementationTest() {
        AnonymousImplementation anonymousImplementation = new AnonymousImplementation();
    }

    /**
     * 对于域（即便是private域），对反射仍旧无任何隐藏
     * 但final域遭遇修改时是安全的，实际上不会发生任何修改。
     */
    @Test
    public void ModifyingPrivateFieldsTest() {
        ModifyingPrivateFields modifyingPrivateFields = new ModifyingPrivateFields();
    }

    /**
     *
     */
    @Test
    public void Test() {

    }
}
