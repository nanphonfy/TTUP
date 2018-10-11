>- Trace跟踪参数  
>- 堆的分配参数  
>- 栈的分配参数

- jvm的GC日志分析  
https://blog.csdn.net/doc_sgl/article/details/46594123

#### Trace跟踪参数  
>-XX:+PrintGC 可以打印GC的简要信息  
-XX:+PrintGCDetails 打印GC详细信息  
-XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）  
-XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）  
-XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息  
-XX:+PrintGCApplicationStoppedTime 输出GC造成应用暂停的时间  
-Xloggc:../logs/gc.log 日志文件的输出路径  
>>在程序末尾加System.gc();即可使参数生效.


- -XX:+printGC
>[GC 3277K->720K(123904K), 0.0031785 secs]
>>每次发生gc，都会打印如上信息。堆在gc前，占用3277k，堆在回收后占用720k，整个堆的大小在12MB左右，花费0.0031785秒。  


- -XX:+PrintGCDetails
>[GC [PSYoungGen: 3277K->720K(37888K)] 3277K->720K(123904K), 0.0015723 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
- 程序运行结束后的状况： 
```
Heap
 PSYoungGen（新生代）      total 37888K（32768K+5120K）, used 983K [0x00000007d6000000（低边界）, 0x00000007d8a00000（当前边界）, 0x0000000800000000（高边界）) （（高边界-低边界）/1024/1024=3.7MB）
  eden space 32768K, 3% used [0x00000007d6000000,0x00000007d60f5cf8,0x00000007d8000000)
  from（等于to） space 5120K, 0% used [0x00000007d8000000,0x00000007d8000000,0x00000007d8500000)
  to（等于from）   space 5120K, 0% used [0x00000007d8500000,0x00000007d8500000,0x00000007d8a00000)
 ParOldGen（老年代）       total 86016K, used 589K [0x0000000782000000, 0x0000000787400000, 0x00000007d6000000)
  object space 86016K, 0% used [0x0000000782000000,0x0000000782093488,0x0000000787400000)
 PSPermGen（永久代）       total 21504K, used 3114K [0x000000077ce00000, 0x000000077e300000, 0x0000000782000000)
  object space 21504K, 14% used [0x000000077ce00000,0x000000077d10a898,0x000000077e300000)
```

`-Xloggc:F:\Java\gc.log`
>指定GC log的位置，以文件输出,帮助开发人员分析问题.


`-XX:+PrintHeapAtGC`
>每次一次GC后，都打印堆信息

```
{Heap before GC invocations=1 (full 0):
 PSYoungGen      total 37888K, used 3277K [0x00000007d6000000, 0x00000007d8a00000, 0x0000000800000000)
  eden space 32768K, 10% used [0x00000007d6000000,0x00000007d6333488,0x00000007d8000000)
  from space 5120K, 0% used [0x00000007d8500000,0x00000007d8500000,0x00000007d8a00000)
  to   space 5120K, 0% used [0x00000007d8000000,0x00000007d8000000,0x00000007d8500000)
 ParOldGen       total 86016K, used 0K [0x0000000782000000, 0x0000000787400000, 0x00000007d6000000)
  object space 86016K, 0% used [0x0000000782000000,0x0000000782000000,0x0000000787400000)
 PSPermGen       total 21504K, used 3108K [0x000000077ce00000, 0x000000077e300000, 0x0000000782000000)
  object space 21504K, 14% used [0x000000077ce00000,0x000000077d1090f0,0x000000077e300000)
Heap after GC invocations=1 (full 0):
 PSYoungGen      total 37888K, used 720K [0x00000007d6000000, 0x00000007d8a00000, 0x0000000800000000)
  eden space 32768K, 0% used [0x00000007d6000000,0x00000007d6000000,0x00000007d8000000)
  from space 5120K, 14% used [0x00000007d8000000,0x00000007d80b4040,0x00000007d8500000)
  to   space 5120K, 0% used [0x00000007d8500000,0x00000007d8500000,0x00000007d8a00000)
 ParOldGen       total 86016K, used 0K [0x0000000782000000, 0x0000000787400000, 0x00000007d6000000)
  object space 86016K, 0% used [0x0000000782000000,0x0000000782000000,0x0000000787400000)
 PSPermGen       total 21504K, used 3108K [0x000000077ce00000, 0x000000077e300000, 0x0000000782000000)
  object space 21504K, 14% used [0x000000077ce00000,0x000000077d1090f0,0x000000077e300000)
}
```

`-XX:+TraceClassLoading`
>监控类的加载
```
[Opened F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.Object from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.io.Serializable from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.Comparable from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.CharSequence from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.String from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.reflect.AnnotatedElement from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
[Loaded java.lang.reflect.GenericDeclaration from F:\Java\jdk1.8.0_71\jre\lib\rt.jar]
...
```

### 堆的分配参数
Xmx=1792.0M
free mem=117.79959869384766M
total mem=121.0M


`-Xmx –Xms`
>指定最大堆和最小堆
>>-Xmx20m -Xms5m  

- 运行代码
```java  
public class T1 {
    public static void main(String[] args) {
        //①啥都没有
        
        /*②byte[] b=new byte[1*1024*1024];
        System.out.println("分配了1M空间给数组");*/
        
        
        /*③byte[] b=new byte[4*1024*1024];
        System.out.println("分配了4M空间给数组");*/
        
        /*④System.gc();*/

        //        系统最大的空间
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");

        //        空闲的空间
        System.out.print("free mem=");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");

        //        系统一启动就占用的空间
        System.out.print("total mem=");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
    }
}
①Xmx=18.0M
free mem=4.682075500488281M
total mem=5.5M

②分配了1M空间给数组
Xmx=18.0M
free mem=3.6999664306640625M
total mem=5.5M（Java会尽可能维持在最小堆
）

③分配了4M空间给数组
Xmx=18.0M
free mem=5.143043518066406M
total mem=10.0M（总内存变多了）

④Xmx=18.0M
free mem=4.8293914794921875M（空闲内存增多）
total mem=5.5M
```

>- 思考问题
>>- -Xmx 和 –Xms 应该保持一个什么关系，可以让系统的性能尽可能的好呢？
>>- 如果你要做一个Java的桌面产品，需要绑定JRE，但是JRE又很大，你如何做一下JRE的瘦身呢？

`-Xmn`
>Xmn是设置绝对值，eg.30MB  
设置新生代大小

`-XX:NewRatio`
>新生代（eden+2*s）和老年代（不包含永久区）的比值  
4 表示 新生代:老年代=1:4，即年轻代占堆的1/5

`-XX:SurvivorRatio`
>设置两个Survivor区和eden的比  
8表示 两个Survivor:eden=2:8，即一个Survivor占年轻代的1/10  

```java  
public class T2 {
    public static void main(String[] args) {
        byte[] b=null;
        for(int i=0;i<10;i++){
            b=new byte[1*1024*1024];
        }
    }
}
```
`Xmx20m -Xms20m -Xmn1m  -XX:+PrintGCDetails`
>没有触发GC  
全部分配在老年代

```
[GC (Allocation Failure) [PSYoungGen: 512K->488K(1024K)] 512K->496K(19968K), 0.0247894 secs] [Times: user=0.00 sys=0.00, real=0.03 secs] 
[GC (Allocation Failure) [PSYoungGen: 1000K->504K(1024K)] 1008K->650K(19968K), 0.0016019 secs] [Times: user=0.01 sys=0.03, real=0.00 secs] 
Java HotSpot(TM) 64-Bit Server VM warning: NewSize (1536k) is greater than the MaxNewSize (1024k). A new max generation size of 1536k will be used.
Heap
 PSYoungGen      total 1024K, used 596K [0x00000000ffe80000, 0x0000000100000000, 0x0000000100000000)
  eden space 512K, 17% used [0x00000000ffe80000,0x00000000ffe97088,0x00000000fff00000)
  from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
 ParOldGen       total 18944K, used 10386K [0x00000000fec00000, 0x00000000ffe80000, 0x00000000ffe80000)
  object space 18944K, 54% used [0x00000000fec00000,0x00000000ff624bf0,0x00000000ffe80000)
 Metaspace       used 3099K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 338K, capacity 386K, committed 512K, reserved 1048576K
```

`-Xmx20m -Xms20m -Xmn15m  -XX:+PrintGCDetails`
>没有触发GC  
全部分配在eden  
老年代没有使用

```
Heap
 PSYoungGen      total 13824K, used 12288K [0x00000000ff100000, 0x0000000100000000, 0x0000000100000000)
  eden space 12288K, 100% used [0x00000000ff100000,0x00000000ffd00000,0x00000000ffd00000)
  from space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
  to   space 1536K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x00000000ffe80000)
 ParOldGen       total 5120K, used 0K [0x00000000fec00000, 0x00000000ff100000, 0x00000000ff100000)
  object space 5120K, 0% used [0x00000000fec00000,0x00000000fec00000,0x00000000ff100000)
 Metaspace       used 3098K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 338K, capacity 386K, committed 512K, reserved 1048576K
```

`-Xmx20m -Xms20m -Xmn7m  -XX:+PrintGCDetails`
>进行了2次新生代GC  
s0 s1 太小需要老年代担保

```
[GC (Allocation Failure) [PSYoungGen: 5555K->488K(6656K)] 5555K->1746K(19968K), 0.0020124 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 5787K->440K(6656K)] 7045K->2722K(19968K), 0.0013429 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 6656K, used 1565K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
  eden space 6144K, 18% used [0x00000000ff900000,0x00000000ffa19798,0x00000000fff00000)
  from space 512K, 85% used [0x00000000fff80000,0x00000000fffee030,0x0000000100000000)
  to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
 ParOldGen       total 13312K, used 2282K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
  object space 13312K, 17% used [0x00000000fec00000,0x00000000fee3ab70,0x00000000ff900000)
 Metaspace       used 3048K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 334K, capacity 386K, committed 512K, reserved 1048576K
```

`-Xmx20m -Xms20m -Xmn7m   -XX:SurvivorRatio=2 -XX:+PrintGCDetails`
>进行了3次新生代GC  
s0 s1 增大

```
[GC (Allocation Failure) [PSYoungGen: 3419K->1512K(5632K)] 3419K->1714K(18944K), 0.0017692 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 4703K->1528K(5632K)] 4905K->1730K(18944K), 0.0013970 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 4696K->1512K(5632K)] 4898K->1722K(18944K), 0.0007342 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 5632K, used 3691K [0x00000000ff900000, 0x0000000100000000, 0x0000000100000000)
  eden space 4096K, 53% used [0x00000000ff900000,0x00000000ffb20e90,0x00000000ffd00000)
  from space 1536K, 98% used [0x00000000ffd00000,0x00000000ffe7a030,0x00000000ffe80000)
  to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
 ParOldGen       total 13312K, used 210K [0x00000000fec00000, 0x00000000ff900000, 0x00000000ff900000)
  object space 13312K, 1% used [0x00000000fec00000,0x00000000fec34b50,0x00000000ff900000)
 Metaspace       used 3048K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 334K, capacity 386K, committed 512K, reserved 1048576K
```

`-Xmx20m -Xms20m -XX:NewRatio=1   
-XX:SurvivorRatio=2 -XX:+PrintGCDetails`

```
[GC (Allocation Failure) [PSYoungGen: 4463K->1752K(7680K)] 4463K->1760K(17920K), 0.0018778 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 5996K->1752K(7680K)] 6004K->1760K(17920K), 0.0014641 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 7680K, used 5050K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 5120K, 64% used [0x00000000ff600000,0x00000000ff938a48,0x00000000ffb00000)
  from space 2560K, 68% used [0x00000000ffd80000,0x00000000fff36020,0x0000000100000000)
  to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
 ParOldGen       total 10240K, used 8K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 0% used [0x00000000fec00000,0x00000000fec02000,0x00000000ff600000)
 Metaspace       used 3059K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 335K, capacity 386K, committed 512K, reserved 1048576K
```

`-Xmx20m -Xms20m -XX:NewRatio=1   
-XX:SurvivorRatio=3 -XX:+PrintGCDetails`

```
[GC (Allocation Failure) [PSYoungGen: 5555K->1808K(8192K)] 5555K->1816K(18432K), 0.0021731 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 7107K->1752K(8192K)] 7115K->1760K(18432K), 0.0019777 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 8192K, used 2939K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 6144K, 19% used [0x00000000ff600000,0x00000000ff728d60,0x00000000ffc00000)
  from space 2048K, 85% used [0x00000000ffe00000,0x00000000fffb6020,0x0000000100000000)
  to   space 2048K, 0% used [0x00000000ffc00000,0x00000000ffc00000,0x00000000ffe00000)
 ParOldGen       total 10240K, used 8K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 0% used [0x00000000fec00000,0x00000000fec02000,0x00000000ff600000)
 Metaspace       used 3048K, capacity 4494K, committed 4864K, reserved 1056768K
  class space    used 334K, capacity 386K, committed 512K, reserved 1048576K
```

`-XX:+HeapDumpOnOutOfMemoryError`
>OOM时导出堆到文件
`-XX:+HeapDumpPath`
>导出OOM的路径

`-Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\Java\a.dump`

`-XX:OnOutOfMemoryError`
>在OOM时，执行一个脚本-XX:OnOutOfMemoryError=F:\Java\jre1.8.0_71\bin\printstack.bat %p

>当程序OOM时，在D:/a.txt中将会生成线程的dump（D:/tools/jdk1.7_40/bin/jstack -F %1 > D:/a.txt）
>>可以在OOM时，发送邮件，甚至是重启程序

- 总结
>根据实际事情调整新生代和幸存代的大小  
官方推荐新生代占堆的3/8  
幸存代占新生代的1/10  
在OOM时，记得Dump出堆，确保可以排查现场问题  

### 永久区分配参数
`-XX:PermSize  -XX:MaxPermSize`
>设置永久区的初始空间和最大空间  
>他们表示，一个系统可以容纳多少个类型  
>使用CGLIB等库的时候，可能会产生大量的类，这些类，有可能撑爆永久区导致OOM  
```java 
public class T3 {
    public static void main(String[] args) {
        //不断产生新的类

        /*for(int i=0;i<100000;i++){
            CglibBean bean = new CglibBean("geym.jvm.ch3.perm.bean"+i,new HashMap());
        }*/
    }
}
```

>打开堆的Dump  
堆空间实际占用非常少  
但是永久区溢出 一样抛出OOM  
如果堆空间没有用完也抛出了OOM，有可能是永久区导致的


### 栈大小分配
`-Xss`
>通常只有几百K  
决定了函数调用的深度  
每个线程都有独立的栈空间  
局部变量、参数 分配在栈上

```java 
public class TestStackDeep {
    private static int count=0;
    public static void recursion(long a,long b,long c){
        long e=1,f=2,g=3,h=4,i=5,k=6,q=7,x=8,y=9,z=10;
        count++;
        recursion(a,b,c);
    }
    public static void main(String args[]){
        try{
            recursion(0L,0L,0L);
        }catch(Throwable e){
            System.out.println("deep of calling = "+count);
            e.printStackTrace();
        }
    }
}
```
- 递归调用  
-Xss128K
>deep of calling = 294  
java.lang.StackOverflowError

-Xss256K
>deep of calling = 859  
java.lang.StackOverflowError