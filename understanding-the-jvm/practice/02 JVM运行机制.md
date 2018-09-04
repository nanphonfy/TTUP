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

- Java栈——栈上分配

```JAVA 
- 堆上分配:每次需要清理空间
public void method(){
	BcmBasicString* str=new BcmBasicString;
	....    
	delete str;
}

- 栈上分配:函数调用完成自动清理
public void method(){
	BcmBasicString str;
	....
}
```
- Java栈——栈上分配  
```JAVA  
public class OnStackTest {
	public static void alloc(){
		byte[] b=new byte[2];
		b[0]=1;
	}
	public static void main(String[] args) {
		long b=System.currentTimeMillis();
		for(int i=0;i<100000000;i++){
			alloc();
		}
		long e=System.currentTimeMillis();
		System.out.println(e-b);
	}
}

-server -Xmx10m -Xms10m
-XX:+DoEscapeAnalysis -XX:+PrintGC
输出结果 5

-server -Xmx10m -Xms10m  
-XX:-DoEscapeAnalysis -XX:+PrintGC
……
[GC 3550K->478K(10240K), 0.0000977 secs]
[GC 3550K->478K(10240K), 0.0001361 secs]
[GC 3550K->478K(10240K), 0.0000963 secs]
564
```
>小对象（一般几十个bytes），在没有逃逸的情况下，可以直接分配在栈上;  
直接分配在栈上，可以自动回收，减轻GC压力;  
大对象或者逃逸对象无法栈上分配.  

### 栈、堆、方法区交互
```JAVA 
//运行时, jvm 把appmain的信息都放入方法区
public class AppMain {
    //main 方法本身放入方法区。
    public static void main(String[] args) {
        Sample test1 = new Sample(" 测试1 ");
        //test1是引用，所以放到栈区里， Sample是自定义对象应该放到堆里面
        Sample test2 = new Sample(" 测试2 ");
        test1.printName();
        test2.printName();
    }
}

 //运行时, jvm 把appmain的信息都放入方法区 
public class Sample {
    private String name;
    //new Sample实例后， name 引用放入栈区里， name 对象放入堆里
    public Sample(String name){
        this.name = name;
    }
    //print方法本身放入 方法区里。
    public void printName() {
        System.out.println(name);
    }
}
```
>运行时数据区
>>- 堆区：sample 实例、Name：测试1
>>- 方法区：Sample(含printName方法)  、AppMain（含main方法）
>>- java栈区：执行main方法的主线程的方法调用栈（局部变量test1）

- 内存模型  
>每一个线程有一个工作内存和主存独立；  工作内存存放主存中变量的值的拷贝
。

`线程执行引擎->assign->线程工作内存->store,write->主内存`  
`主内存->read、load->线程工作内存->use->线程执行引擎`

>- 当数据从主内存复制到工作存储时，必须出现两个动作：第一，由主内存执行的读（read）操作；第二，由工作内存执行的相应的load操作；当数据从工作内存拷贝到主内存时，也出现两个操作：第一个，由工作内存执行的存储（store）操作；第二，由主内存执行的相应的写（write）操作;
>- 每一个操作都是原子的，即执行期间不会被中断；
>- 对于普通变量，一个线程中更新的值，不能马上反应在其他变量中；  
如果需要在其他线程中立即可见，需要使用 volatile 关键字。  





