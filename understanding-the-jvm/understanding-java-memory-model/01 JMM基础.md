### 基础
#### 并发编程模型分类
>并发编程中，需处理两关键点：①线程间如何通信；②线程间如何同步。  
通信指：线程间以何种机制交换信息。  
命令式编程，线程间通信机制：①内存共享；②消息传递。

>共享内存并发模型：线程间共享程序公共状态(通过它隐式通信)；  
消息传递并发模型：线程间无公共状态(需明确发送消息显示通信)。

>同步：程序用于控制不同线程间操作发生相对顺序的机制。 
>>在共享内存并发模型，同步是显示的(需指定某个方法或代码块进行线程间互斥)；  
在消息传递并发模型，同步是隐式的（消息发送在接收前）。
>- java的并发采用共享内存模型，java线程间的通信为隐式。

#### java内存模型的抽象
>堆内存（共享变量）：实例域、静态域和数组元素，在线程间共享。  
局部变量、方法定义参数、异常处理器参数不会在线程间共享（不会有内存可见性问题）  

>java线程间通信由java内存模型(JMM)控制，它决定了一个线程对共享变量的写入何时对另一个线程可见。  
>>线程间共享变量存储在主内存中，每个线程都有私有的本地内存（读/写共享变量的副本，抽象概念不真实存在）。JMM涵盖了缓存、写缓冲区、寄存器及其他的硬件和编译器优化。

- 图1
![java内存模型抽象](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/01/jmm-abstract-view.png)
>线程A与线程B通信，步骤：  
①线程A把本地内存A中更新过的共享变量刷新到主内存；  
②线程B到主内存读取线程A已更新过的共享变量。

- 图2
![java内存模型抽象2](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/01/jmm-abstract-view2.png)
>假若本地内存A、B都有主内存的共享变量x的副本(默认都为0)。  
①线程A更新x值为1，临时存放本地内存A；  
②线程A和线程B需通信时，线程A会把修改后的x值刷新到内存，随后线程B到主内存读取线程A更新后的值。  
两步骤是线程A向线程B发送消息，通信过程经过主内存。  
JMM通过控制主内存与每个线程本地内存间的交互，来提供内存可见性保证。

#### 重排序
>- ①编译器优化重排序（不改变单线程程序语义）；  
>- ②指令级重排序（现代处理器指令级并行技术，将多条指令重叠执行，不存在数据依赖时）；  
>- ③内存系统重排序（处理器缓存和读/写缓冲区，使得操作看上去可能乱序）。  

- java源代码到最终实际执行的指令序列：
`源代码->①->②->③->最终执行的指令序列`

- 编译器重排序：①   
- 处理器重排序：②、③  
>都可能导致多线程程序出现内存可见性问题。

>JMM编译器重排会禁止特定类型的编译器重排序（不是所有）；  
处理器重排会在编译器生成指令序列时，插入特定类型的内存屏障指令，禁止特定类型处理器重排（不是所有）。  
JMM属语言级内存模型。

#### 处理器重排序与内存屏障指令

>处理器使用写缓冲区临时向内存写数据(可保证指令流水线持续运行)，避免处理器停顿导致向内存写数据延迟。  
批处理刷新写缓冲区，合并写缓冲区中对同一内存地址的多次写（减少占用内存总线）。  
>>缺点：仅仅对写缓冲区所在的处理器可见，对内存操作的顺序产生重要影响：处理器对内存的读/写操作执行顺序，不一定与内存实际顺序一致。


处理器A | 处理器B
---|---
a=1; //A1 | b=1;//B1
x=b; //A2 | y=a;//B2
初始状态：a=b=0 处理器执行后得到结果：x=y=0|

- 图3  
![处理器重排序](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/01/processor_reorder.png)

>①处理器A、B同时把共享变量写入自己的写缓冲区(A1、B1)；
②从内存读取另一个共享变量(A2、B2)；
③将写缓冲区保存的脏数据刷新到内存（A3、B3）；
④x=y=0。

>从内存实际操作顺序看，处理器A在A3才刷新写缓冲区，写操作A1才算真正执行。  
虽处理器A执行内存顺序：A1->A2，但实际内存顺序：A2->A1，此时处理器A的内存操作顺序被重排序了（同理处理器B）。
>>由于写缓冲区仅对自己的处理器可见，导致处理器执行内存操作顺序与内存实际~不一致。


##### 内存屏障指令
>为保证内存可见性，java编译器生成指令序列的适当位置会插入内存屏障指令禁止特定类型的处理器重排序。

- JMM内存屏障指令分类

屏障类型 | 指令示例 | 说明
---|---|---
LoadLoad Barriers | Load1;LoadLoad;Load2 | Load1数据指令装载在Load2之前(及后续装载指令的装载)
StoreStore Barriers | Store1;StoreStore;Store2 | Store1数据对其他处理器可见(刷新到内存)，在Store2之前(及后续存储指令的存储)
LoadStore Barriers | Load1;LoadStore;Store2 | Load1数据装载在Store2之前(及后续存储指令刷新到内存)
StoreLoad Barriers | Store1;StoreLoad;Load2 | Store1数据对其他处理器可见(刷新到内存)，在Load2之前(及后续装载指令的装载)

>StoreLoad Barriers是“全能型”屏障，具有其他三个屏障的效果（大都处理器都支持，其他类型的屏障不一定支持）。  
执行该屏障开销很昂贵，要把写缓冲区中的数据全部刷新到内存中。

#### happens-before
>JMM中，若一个操作执行结果需对另一个操作可见，那两操作（一个线程内或不同线程间）间必须存在happens-before关系。

>- 程序顺序规则：一个线程的每个操作，happens-before于该线程任意后续操作；
>- 监视器锁规则：对一个监视器的解锁，happens-before于随后对这个监视器的加锁；
>- volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读；
>- 传递性：A happens-beforeB，B happens-before于 C，那么A happens-before于 C。
>happens-before仅要求前一个操作(执行的结果)对后一个操作可见，并不必须要在后一个操作前执行（前一个操作按顺序排在第二个操作前）。  

- happens-before与JMM关系图
![happens-before与JMM关系图](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/01/happens-before-and-jmm.png)

>一个happens-before规则对应一或多个编译器和处理器重排序规则，其规则简单易懂。
