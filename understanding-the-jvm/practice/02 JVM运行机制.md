>JVM启动流程  

JVM基本结构  

内存模型  

编译和解释运行的概念  

- JVM启动流程  
`Java XXX
->装载配置(根据当前路径和系统版本寻找jvm.cfg)->根据配置寻找JVM.dll
(JVM.dll为JVM主要实现)->初始化JVM
获得JNIEnv接口(JNIEnv为JVM接口，findClass等操作通过它实现)->找到main方法
并运行`

### JVM基本结构
![image](https://images2017.cnblogs.com/blog/607455/201708/607455-20170807200944768-1132314845.png)

- PC寄存器  

>每个线程拥有一个PC寄存器；  

在线程创建时 创建
指向下一条指令的地址；  

执行本地方法时，PC的值为undefined。

- 方法区

>- 保存装载的类信息  
>类型的常量池  
字段，方法信息  
方法字节码  
>>JDK6时，String等常量信息置于方法区；
JDK7时，已经移动到了堆。
>- 通常和永久区(Perm)关联在一起
。

- Java堆
>和程序开发密切相关；  
应用系统对象都保存在Java堆中；  
所有线程共享Java堆;  
对分代GC来说，堆也是分代的；  
GC的主要工作区间。

- Java栈  
>线程私有；  
栈由一系列帧组成（因此Java栈也叫做帧栈）；  
帧保存一个方法的局部变量、操作数栈、常量池指针；  
每一次方法调用创建一个帧，并压栈。

- Java栈——局部变量表 包含参数和局部变量

```JAVA 
public class StackDemo {
    public static int runStatic(int i,long l,float f,Object o ,byte b){
        return 0;
    }
    
    public int runInstance(char c,short s,boolean b){
        return 0;
    }
}
```

序号 | 空间 |参数 
---|---|---|
0 | int |int i
1 | long |long l
2 | float |float f
3 | reference |Object o
4 | byte |byte b

序号 | 空间 |参数 
---|---|---|
0 | reference |this
1 | int |char c
2 | int |short s
3 | int |boolean b

- Java栈——函数调用组成帧栈  

```JAVA  
public static int runStatic(int i,long l,float  f,Object o ,byte b){
    return runStatic(i,l,f,o,b);
}
```
序号 | 空间 |参数 
---|---|---|
0 | int |int i
1 | long |long l
2 | float |float f
3 | reference |Object o
4 | byte |byte b
>这是一个帧，省略：操作数栈 返回地址等.

序号 | 空间 |参数 
---|---|---|
0 | int |int i
1 | long |long l
2 | float |float f
3 | reference |Object o
4 | byte |byte b
...
- Java栈——操作数栈  
>Java没有寄存器，所有参数传递使用操作数栈

```JAVA  
public static int add(int a,int b){
	int c=0;
	c=a+b;
	return c;
}

 0:   iconst_0 // 0压栈
 1:   istore_2 // 弹出int，存放于局部变量2
 2:   iload_0  // 把局部变量0压栈
 3:   iload_1 // 局部变量1压栈
 4:   iadd      //弹出2个变量，求和，结果压栈
 5:   istore_2 //弹出结果，放于局部变量2
 6:   iload_2  //局部变量2压栈
 7:   ireturn   //返回
```
时间轴 |局部变量 |操作数栈  
---|---|---
开始之前 |0->100,1->98 |空  
iload_0指令之后 |0->100,1->98 |0->100
iload_1指令之后 |0->100,1->98 |0->100,1->98
iadd指令之后 |0->100,1->98 |0->198
istore_2指令之后 |0->100,1->98,3->198 |空







