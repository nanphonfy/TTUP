## java内存区域与内存溢出异常  
### 运行时数据区域  
![image](http://image.mamicode.com/info/201802/20180217003758106534.png)
- 程序计数器
>是一块较小的内存空间，可看作当前线程所执行的字节码的行号指示器。字节码解释器通过改变计数器的值选取执行字节码指令。  
>>JVM的多线程通过线程轮流切换并分配处理器执行时间实现，故任何一个确定时刻，一个处理器只会执行一条线程中的指令。为了线程切换后能恢复到正确的执行位置，每条线程都需一个独立的程序计数器，各线程计数器互不影响，独立存储。（线程私有内存）
>- 正执行java方法，计数器记录的是虚拟机字节码指令地址；正在执行native方法，计数器值为空（undefined）。

- java虚拟机栈
>描述了java方法执行的内存模型：方法执行时都会创建栈帧（存储局部变量表、操作数栈、动态链接、方法出口等）。方法从调用到执行完成，对应栈帧在虚拟机栈入栈到出栈的过程（线程私有，生命周期与线程相同）。
>>局部变量表存放编译期可知的基础数据类型（boolean、byte、char、short、int、float、long、double）、对象引用（reference类型，指向对象起始地址的引用指针or执行代表对象的句柄or其他与此对象相关的位置）和returnAddress类型（指向字节码指令地址）。