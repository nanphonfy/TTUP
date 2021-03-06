- 静态变量和实例变量的区别
>语法：static和不用static；  
运行时区别：实例变量属于实例对象，静态变量属于类（类变量），只要程序加载了类的字节码，不用创建任何实例，即可使用。

- 是否可以从一个static方法内部发出对非static方法的调用
>不可以。因为非static方法要与对象关联在一起，必须创建一个对象后，才可在该对象上进行方法调用，而static方法调用时不需创建对象，可直接调用。

- Integer与int的区别
>int是java提供的8种原始数据类型之一。
Java为每个原始类型提供了封装类，Integer是java为int提供的封装类。
int的默认值为0，而Integer的默认值为null;
jsp开发中，当学生没有参加考试，el表达式的Integer为空，而int则为0。

- java作用域  

作用域|当前类|同一包|子孙类|其他包
---|---|---|---|---
public|√|√|√|√  
protected|√|√|√|×  
friendly|√|√|×|×  
private|√|×|×|×  

- Overload和Override的区别  
>overload（重载）:
重载的概念是：
①方法名称相同，参数个数、次序、类型不同；②因此重载对返回值没有要求，可以相同，也可以不同；③但是如果参数的个数、类型、次序都相同，方法名也相同，仅返回值不同，则无法构成重载。 

eg：
```java  
//这2个方法不能构成重载，会有编译错误(Java无法确定编程者倒底是想调用哪个方法)
public int A(int i);
public double A(int i);

//而这2个方法可以形成重载：
public int A(int i):
public double A(double i);
```
>override（覆盖|重写）:
面向对象编程的多态性的一种表现。
子类覆盖父类的方法时，只能比父类抛出更少的异常，或者是抛出父类抛出的异常的子异常；子类方法的访问权限只能比父类的更大，不能更小。eg.父类方法是private，那么相当于子类中增加了一个全新方法，而不能重写。
最熟悉的覆盖就是对接口方法的实现，继承中也可能会在子类覆盖父类中的方法。

- 构造器Constructor是否可被override
>构造器Constructor不能被继承，因此不能重写Override，但可以被重载Overload

- clone()方法的缺省行为  
>super.clone();首先要复制父类的成员，然后才复制自己的成员。

- 面向对象特征：封装、抽象、继承、多态
>**封装：** 模块化基础，目标：高内聚低耦合。让变量和访问这个变量的方法放在一起，成员变量全部私有，只有类自己的方法才可访问，即为对象的封装。即将对象封装成一个高度自治和相对封闭的个体，对象状态（属性）由这个对象自己的行为（方法）来读取和改变。  
**抽象：** 找出一些事物相似和共性之处，将其归为一个类，包括行为抽象和状态抽象。使用抽象，就要善于划分问题边界，当系统给需要什么，就只考虑什么，而不是面面俱到。  
**继承：** 是子类自动共享父类数据和方法的机制，是类之间的一种关系，提高可重用性和可扩展性。  
**多态：** 指定义的引用变量到底会指向哪个类的实例对象，必须在运行期间才能决定，可在不修改代码下就可改变程序运行时才确定的具体类。增强灵活性和扩展性。

- java实现多态的机制
>靠父类或接口定义的引用变量可指向子类或具体实现类的实例对象，而程序调用的方法在运行期才动态绑定（所指向的具体实例对象的方法）。

- native方法
>表示该方法要用另外一种依赖平台的编程语言实现的，eg.，FileOutputSteam类要硬件打交道，底层的实现用操作系统相关的api实现。若要用java调用别人写的c语言函数，无法直接调用的，需按java要求写一个c语言的函数，由这个c语言函数去调用别人的c语言函数。

- 运行时异常VS一般异常
>java编译器要求方法必须声明抛出可能发生的++非运行时异常++，并不要求必须声明抛出未被捕获的++运行时异常++（表示虚拟机的通常操作中可能遇到的异常，是一种常见的运行错误）

- Error VS Exception
>error表示恢复不是不可能的但很困难（严重问题，eg.内存溢出、线程死锁，要人工解决）   
exception表示一种设计或实现问题，若程序运行正常，从不会发生的情况。

- Java异常处理机制的简单原理和应用
>从三个级别分析：虚拟机必须宕机、程序可死可不死，程序不该死的错误。
>- 异常指java运行时（非编译）所发生的非正常情况或错误。
>- java对异常进行了分类，所有异常的根类java.lang.Throwable，派生了Error（程序本身无法克服和恢复的一种严重问题，程序只有死的份，eg.内存溢出、线程死锁等系统问题）和Exception（程序还能够克服和恢复的问题）子类。
>- Exception又分系统异常（开发人员考虑不周，软件本身缺陷导致，用户无法克服恢复，但还可以继续运行或让软件死掉，eg.数组越界、空指针异常、类转换异常等）和普通异常（运行环境的变化或异常导致，程序不应死掉，用户能克服，eg.网络断线、硬盘空间不够等）
>- ①系统异常，即运行时异常（总是由虚拟机接管），可处理也可不处理，故编译器不强制用try..catch处理或用throws声明，unchecked 异常；
②普通异常，编译器强制必须try..catch 处理或用throws声明抛给上层调用方法处理，checked 异常。

- 最常见的5个runtime exception
>所谓系统异常，就是可能在java虚拟机正常工作时抛出的异常，它们都是RuntimeException的子类，有NullPointerException（空指针）、ArrayIndexOutOfBoundsException（数组越界）、
ClassCastException（类转换异常）、ArrayStoreException（数据存储异常，操作数组时类型不同）、[BufferOverflowException]（http://blog.csdn.net/wletv/article/details/8707070）IO异常，缓冲区溢出。

- java语言的异常处理
>throws 捕获并向外抛出异常、throw 抛出异常、try catch 是内部捕获异常并做自定义处理finally 是无论是否有异常都会被处理，除非在finally前存在System.exit(int i)

- stop()和suspend()方法为何不推荐使用
>- stop不安全，它会解除由线程获取的所有锁定，且当对象处于一种不连贯状态，那么其他线程能在那种状态下检查和修改它们，很难查出问题所在。
>- suspend()容易发生死锁，调用时目标线程会停下来，但却仍然持有在这之前获得的锁定。此时，其他任何线程都不能访问锁定的资源，除非被"挂起"的线程恢复运行。
>- 应在自己的Thread类中置入一个标志（活动|挂起），若标志指出线程应挂起，便用 wait()命其进入等待状态。若标志指出线程应当恢复，则用一个notify()重新启动线程。

- 同步VS异步
>- 若数据将在线程间共享，eg.正在写的数据以后可能被另一个线程读，或正在读的数据可能已被另一个线程写过了，那么这些数据就是共享数据，必须进行同步存取。
>- 当程序调用一个需花费很长时间执行的方法， 且不希望等待方法的返回时，就应使用异步编程，更有效率。

- 同步的几种实现方法
>分别是synchronized,wait 与 notify。
>- wait():使一个线程处于等待状态，并且释放对象lock。
>- sleep():使一个正在运行的线程处于睡眠状态(静态方法)，调用要捕捉InterruptedException(中断异常)异常。
>- notify():唤醒一个处于等待状态的线程(在调用此方法时，并不能确切的唤醒，而是由JVM确定唤醒哪个线程，不按优先级)
>- notifyAll():唤醒所有处入等待状态的线程(不是给所有唤醒线程一个对象锁，而是让它们竞争)

- 线程进入synchronized方法后，其它线程是否可进入此对象的其它方法
>①若其他方法都没加synchronized，则能；
②若该方法内部调用了wait，则可进入其他synchronized方法；
③若其他方法都加了synchronized，且该方法内部没调用wait，则不能；
④若其他方法是static（同步锁是当前类的字节码），非静态方法（用的是this）。

- 线程概念
>一个程序中可有多条执行线索(线程)同时执行，每个线程都关联有要执行的代码，即可以有多段程序代码同时运行，每个程序至少有一个main方法的主线程。若只有单个cpu，宏观上，cpu一会执行a线索，一会执行b线索，切换时间很快，给人的感觉是a,b同时执行。

- 线程的基本状态和状态关系
>状态：就绪、运行、synchronize阻塞、wait（wait必须在synchronized内部调用）和sleep挂起、结束。
```
调用线程的start方法->就绪状态
线程调度系统转为->运行状态
遇到synchronized语句->阻塞
当synchronized获得锁（该情况可调用wait转为挂起）->运行
线程关联的代码执行完后->结束
```

- [Collection框架的结构](http://www.cnblogs.com/yadongliang/p/5351255.html)  
>Collection
>>**List：** ArrayList、LinkedList、Vector、Stack   
**Set：** HashSet、TreeSet   
**Map：** HashMap、HashTable、TreeMap、WeakHashMap

- ArrayList VS Vector
>**共同点：** 都实现List（继承Collection接口）接口，都是有序集合（相当于动态数组，按位置索引号取元素），允许重复。  
**异同点：** 
>- ①同步性：Vector是线程安全的(java1)，方法间是线程同步的，若有多个线程访问集合，最好用它；ArrayList是线程不安全的(java2)，方法间是不同步的，效率高点，若只有一个线程访问，最好用它。
>- ②数据增长：都有初始容量大小，存储空间满时会扩容，要在内存空间与程序效率间取得一定平衡。 他们都可设置初始大小，Vector还**可**设置增长空间大小，默认增长一倍，ArrayList**不可**设置增长大小，默认增长0.5倍。

- HashMap VS Hashtable
>**共同点：** HashMap是Hashtable的轻量级实现，都实现Map接口（java1.2引入），它们采用的hash/rehash算法大概一样，故性能差异不大。
**异同点：**
>- ①历史：Hashtable继承了陈旧的Dictionary类，HashMap把它的contains改成containsValue和containsKey；
>- ②同步性：Hashtable的方法是synchronized的，而HashMap不是；
>- ③值：只有HashMap运行null做为key或value，Hashtable会出现NullPointerException。

- List VS Map
>List是存储单列数据集合，存储的数据有顺序，允许重复；Map是存储键和值的双列数据的集合，键不能重，值可重。

- List 、Map 、Set存取元素各有什么特点
>List和Set都是单列元素的集合，都有共同的父接口Collection。
>- Set不允许重复，当集合含有与某元素equals相等的元素时，add会返回false。遍历Set无法通过下标索引，只能以iterator接口取得所有元素，再逐一遍历各个元素。
>- List有存储顺序，可插队（调用add(int index, E element)），在集合中用一个索引变量指向对象。遍历元素时除了 Iterator接口，也可通过get方法。
>- Map与List和Set不同，它是双列集合，不能有重复key（也是按equals是否相等）。遍历：可通过keySet、entrySet使用iterator、entrySet、values等4种方法遍历。
>>HashSet按hashcode值的某种运算方式进行存储，而不是直接按hashcode值的大小存储。LinkedHashSet按插入的顺序存储， 那被存储对象的hashcode还有什么作用呢？Hashset 集合比较两个对象是否相等，首先看hashcode是否相等，再看equals方法是否相等。

- ArrayList、Vector、LinkedList的存储性能和特性
>ArrayList和LinkedList都是线程不安全的，其中ArrayList和Vector使用数组存储数据，LinkedList（提供了一些方法，可被当做堆栈和队列）使用双向链表存储。前者可按序号索引元素，插入涉及内存移动操作，索引数据快插入数据慢,LinkedList按序号索引数据要向前或向后遍历，但插入数据很快。

- Collection VS [Collections](http://blog.csdn.net/yangfeixien/article/details/40391771)
>Collection是集合类的上级接口，Set和List都继承它；  
Collections是针对集合类的一个帮助类，他提供一系列静态方法实现对各种集合的搜索、排序、线程安全化、打散、二叉搜索、copy、等操作。