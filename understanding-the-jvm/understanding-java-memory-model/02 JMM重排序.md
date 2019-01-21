#### 数据依赖性
若两操作（有一个写操作）访问同一变量，此时它们就存在数据依赖性。
- 数据依赖分三类：
名称| 代码示例| 说明
---|---|---
写后读|a=1;b=a;|
写后写|a=1;a=2;|
读后写|a=b;b=1;|

>上面三种，只要重排序，程序执行结果就会改变。  
>>编译器和处理器在重排序时，会遵守数据依赖性，不会改变存在数据依赖关系（仅针对单个处理器执行的指令序列和单个线程执行操作）的两个操作执行顺序。

#### as-if-serial语义
>指不管怎么重排序，（单线程）程序的执行结果不能被改变。编译器、runtime和处理器都必须遵守as-if-serial语义。

- 计算圆的面积
```
double pi = 3.14; //A
double r = 1.0; //B
double area = pi*r*r;//C
```

>上面三操作的数据依赖关系：A->C、B->C  
C不能被重排序到A和B的前面，而A和B无数据依赖关系，可重排序A、B。

>①A->B->C；  
②B->A->C；  
>>as-if-serial语义把单线程程序保护起来，使编译器、runtime和处理器产生幻觉：单线程程序是按顺序执行。  
使程序员无需担心重排序和内存可见性问题。

#### 程序顺序规则
>根据happens-before的程序顺序规则，根据传递性分析以上代码的关系：  
①A happens-before B；  
②B happens-before C；  
③A happens-before C。

>实际上B可排在A前。如果A happens-before B，JMM并不要求A一定要在B前执行，仅要求前一个操作（执行结果）对后一操作可见，且前一操作按顺序排在第二个操作之前。  
A->B->C和B->A->C执行结果一致，故JMM认为该种重排序不非法。  
计算机中，软件、硬件技术都有共同目标：在不改变程序执行结果的前提下，尽可能的开发并行度（编译器和处理器、JMM都遵从）。

#### 重排序对多线程的影响
```java 
public class ReorderExample {
    int a = 0;
    boolean flag = false;

    void write() {
        a = 1;//1
        flag = true;//2
    }

    void read() {
        if (flag) {//3
            int i = a * a;//4
            //...
        }
    }
}
```
>flag变量用于标记变量a是否已被写入。  
假设A、B两线程，A先执行writer，B再执行reader方法，B执行操作4时，能否看到A在操作1对共享变量a的写入？不一定。
>>操作1和2无数据依赖关系，操作3和4也无数据依赖关系，编译器和处理器可对两操作重排序。

-- 操作1、2的多线程重排序  
![操作1、2的多线程重排序](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/02/multi-thread-reorder.png)  
>线程A首先写flag，随后线程B读该变量，由于条件为真，线程B读取变量a，此时变量a还没被线程A写入，这里的多线程程序语义被重排序破坏了。

-- 操作3、4的多线程重排序   
![操作3、4的多线程重排序](https://raw.githubusercontent.com/nanphonfy/note-images/master/TTUP/understanding-the-jvm/understanding-java-memory-model/02/multi-thread-reorder2.png)  

>程序中，操作3、4存在控制依赖关系（会影响执行序列执行的并行度）。为此，编译器和处理器会采用猜测执行，克服控制相关性对并行度的影响。

- 处理器的猜测执行
>①线程B的处理器可提前读取并计算a*a；  
②把计算结果临时保存在重排序缓冲的硬件缓存中；  
③当操作3判断为真，把结果写入变量i。
>>猜测执行实质上对操作3、、4做了重排序（破坏了多线程程序语义）。  

在单线程程序，对存在控制依赖的操作重排序不会改变执行结果（as-if-serial语义允许对存在控制依赖的操作做重排序的原因）；
在多线程程序，对存在控制依赖的操作重排序，可能会改变程序的执行结果。


