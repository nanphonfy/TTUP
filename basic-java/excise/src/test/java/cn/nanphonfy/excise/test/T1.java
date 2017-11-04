package cn.nanphonfy.excise.test;

import org.junit.Test;

import java.util.StringTokenizer;

/**
 * @author nanphonfy(南风zsr)
 * @date 2017/10/28.
 */
public class T1 extends AbstractTest {
    @Test
    public void andTest() {
        //&和&&都可作逻辑与（and）运算符
        int x = 1, y = 0;
        //&表达式两边都为true时，才为true
        if (x == 1 & ++y > 0) {
            logger.info("{}", y);//1
        }
        //&还可作位运算符(当&操作符两边的表达式不是boolean时，&表示按位与操)
        logger.info("{}", 0x11 & 0x12);//1+16=17,2+16=18,10001&10010=10000=16

        //短路功能，第一个表达式为true不再计算第二个
        if (x == 1 && ++y > 0) {
            logger.info("{}", y);//2
        }
    }

    /**
     * Object类介绍
     */
    @Test
    public void objTest() {
        Object object = new Object();
        Object object2 = new Object();
        //1971489295,985655350
        logger.info("{},{}", object.hashCode(), object2.hashCode());
        //false
        //return (this == obj);
        logger.info("{}", object.equals(object2));
        //java.lang.Object@75828a0f
        //getClass().getName() + "@" + Integer.toHexString(hashCode())
        logger.info("{}", object.toString());
        //class java.lang.Object
        logger.info("{}", object.getClass());

        //        protected native Object clone() throws CloneNotSupportedException;
        //        public final native void notify();
        //        public final native void notifyAll();
        //        public final native void wait(long timeout) throws InterruptedException;
        //        public final void wait(long timeout, int nanos) throws InterruptedException
        //        public final void wait() throws InterruptedException
        //        protected void finalize() throws Throwable
    }

    /**
     * 如何跳出当前的多重嵌套循环
     * 从二维数组中找到某个数字
     */
    @Test
    public void arrTest() {
        int arr[][] = { { 1, 2, 3 }, { 4, 5, 6, 7 }, { 9 } };
        boolean found = false;
        for (int i = 0; i < arr.length && !found; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 5) {
                    found = true;
                    break;
                }
            }
        }
    }

    /**
     * switch语句中的表达式只能是char, byte, short, int, Character, Byte, Short, Integer, String以及枚举（enum），
     * eg.byte可隐含转换为int类型，而long字节比int字节多，不能隐式转化为int类型
     * 在JDK7.0中引入新特性，String可作用在switch语句上
     */
    @Test
    public void switchTest() {
        //required: 'char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
        //long i = 1;
        byte i=1;
        switch (i) {
        case 1:
            logger.info("enter");//enter
            break;
        default:
        }
        String str = "apple";
        switch (str) {
        case "apple":
            logger.info("apple");//apple
            break;
        default:
        }
    }

    /**
     * java类型的自动提升
     */
    @Test
    public void automaticPromotionTest() {
        short s1 = 1;
        //编译错误，因为s1+1运算时会自动提升表达式的类
        //Incompatible types.Required:short Found:int
        //s1 = s1 + 1;

        //编译成功，"+="是java语言规定的运算符，会对其进行特殊处理
        s1 += 1;
    }

    /**
     * char型使用Unicode编码（2字节，字符集中包含汉字，除某些特别汉字没有包含外）字符的
     */
    @Test
    public void charTest() {
        char str = '中';
        logger.info("{}", str);
    }

    /**
     * 最高效的乘法运算方式，位的左移
     * 将一个数左移n位，就相当于乘以了2的n次方
     * 位运算cpu直接支持，效率最高
     */
    @Test
    public void mostMultiEfficiency() {
        long start=System.nanoTime();
        int total0=2*8;
        //12632 ns
        logger.info("{}",System.nanoTime()-start);

        start=System.nanoTime();
        int total1=2<<3;
        //790 ns
        logger.info("{}",System.nanoTime()-start);
    }

    /**
     * 设计一个一百亿的计算器
     * 加减法的位运算原理和算术运算的越界问题
     * byte(-128到+127)，-1->11111111，两个-1相加利用溢位实现负数运算11111110，-128->10000000，
     * 两个-128相加，进位后超过byte的存储空间，得到结果为00000000。
     */
    @Test
    public void millionCalculatorTest() {
        int a = Integer.MAX_VALUE;//0x7fffffff,2^31-1,约为20亿
        int b = Integer.MAX_VALUE;//0x7fffffff,2^31-1
        int sum = a + b;//-2
        logger.info("{}", sum);
    }

    /**
     * final关键字修饰一个变量时，引用不能变，引用的对象可变
     */
    @Test
    public void finalTest() {
        final StringBuffer sb = new StringBuffer();
        //编译异常，can not assign a value to final variable 'sb'
        //sb=new StringBuffer();
        sb.append("test");
    }

    /**
     * 在Java中，String 、Math、Integer、Double等封装类重写了Object中的equals()方法，让它不再比较其对象在内存中的地址,而比较的是内容。
     * Object的equals()方法比较的是地址值，所以Object equals相等时，其hashcode必然相等，因为都是对象的地址
     * 所以自己定义的类如果要加入到集合类中一定要记得重写这两个方法。
     */
    @Test
    public void equalsTest() {
        //new String占用一块堆内存，变量占用另外一块内存。
        //变量obj所对应的内存中存储的数值就是对象占用的那块内存的首地址
        String obj = new String("test");
        //equals方法是用于比较两个独立对象的内容是否相同
        //若一个类没有自己定义equals方法，那么它将继承Object类的equals方法（对比的是地址是否相等）

        /**
         【关于string类型是不可改变的问题： 】
         string类型是不可改变的，当想改变一个string对象时，eg.name= "madding"
         那么虚拟机不会改变原来的对象，而是生成一个新的string对象，然后让name去指向它
         若原来的"tom "没任何对象引用它，虚拟机的垃圾回收机制将接收它。
         */
        //使用了串池,当声明一个内容也是 "tom "的字符串时，将会指向同一块内存
        String name0= "tom ";
        //普通声明对象
        String name1 =new String( "tom ");
    }

    /**
     * Math取整：ceil（天花板，向上取整），floor（地板，向下取整），round（四舍五入）
     */
    @Test
    public void MathTest(){
        double n0 = 1.2, n1 = -1.5;
        logger.info("{},{}", Math.ceil(n0), Math.ceil(n1));//2.0,-1.0
        logger.info("{},{}", Math.floor(n0), Math.floor(n1));//1.0,-2.0
        logger.info("{},{}", Math.round(n0), Math.round(n1));//-1,-1
    }

    /**
     * String 类是 final 类型的(所有对象都是不可变对象)，不可继承、不能修改
     * 为提高效率节省空间，应用StringBuffer类
     * 优点:是只读的，多线程并发访问不会有任何问题。
     * 缺点:每个不同的状态都要一个对象来代表，牺牲性能
     */
    @Test
    public void StringTest(){
        long start = System.nanoTime();
        //创建多个对象，放在字符串常量缓冲区(误区)
        //实际上，字符串常量相加，javac会进行优化，直接把+去掉，然后进行拼接
        String s = "i " + "am " + "learning " + "now.";
        //cost 12237 ns
        logger.info("{},cost {} ns", s, System.nanoTime() - start);

        start = System.nanoTime();
        //对象可改变
        StringBuffer sb = new StringBuffer();
        s = sb.append("i ").append("am ").append("learning ").append("now.").toString();
        //cost 51316 ns
        logger.info("{},cost {} ns", s, System.nanoTime() - start);

        //可能创建了1个或2个对象，"xyz"放在字符串常量缓冲区，若存在不创建，new 不管如何都创建一个新的String对象
        String str = new String("xyz");
    }

    /**
     * String覆盖了equals和 hashCode方法
     * StringBuffer没覆盖equals和hashCode 方法(存储进 Java 集合类中时会出现问题)
     */
    @Test
    public void StringAndStringBufferTest(){
        //true，其重写了equals
        logger.info("{}", new String("abc").equals(new String("abc")));
        //false，因其没重写equals
        logger.info("{}", new StringBuffer("abc").equals(new StringBuffer("abc")));


        long start = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100; i++) {
            sb.append(i);
        }
        //cost 97500 ns
        logger.info("{},cost {} ns", sb.toString(), System.nanoTime() - start);

        start = System.nanoTime();
        String str = "";
        for (int i = 0; i < 100; i++) {
            str = str + i;//该处用对象的引用相加，会频繁创建对象（字符串常量池缓冲区）
        }
        //cost 252238 ns
        logger.info("{},cost {} ns", str, System.nanoTime() - start);
    }

    /**
     * 如何把一段逗号分割的字符串转换成一个数组
     */
    @Test
    public void arraySplitTest(){
        String str = "i,want,to,study,.";
        //return value.length;
        //private final char value[];
        str.length();
        //正则表达式
        String[] arr1 = str.split(",");
        int size1 = arr1.length;

        //StingTokenizer
        StringTokenizer tokener = new StringTokenizer(str, ",");
        String[] arr2 = new String[tokener.countTokens()];
        int i = 0;
        while (tokener.hasMoreElements()) {
            arr2[i++] = tokener.nextToken();
        }
    }

    /**
     * javac编译可对字符串常量直接相加的表达式进行优化
     * 不必等到运行期加法运算，而在编译时去掉其中的加号，直接将其编译成一个这些常量相连的结果
     */
    @Test
    public void StringHasObjectTest(){
        String s1 = "a";
        String s2 = s1 + "b";
        String s3 = "a" + "b";//只创建了一个对象
        //false,true
        logger.info("{},{}", s2 == "ab", s3 == "ab");
    }

    /**
     * try {}里有一个 return 语句，finally {}里的code会不会被执行，什么时候被执行
     * finally 中的代码比 return 和 break 语句后执行
     */
    @Test
    public void tryReturnAndFinallyReturnTest(){
        //true
        logger.info("{}",tryFinallRreturn());
    }

    private boolean tryFinallRreturn(){
        boolean flag = false;
        try {
            logger.info("try...");
            //存在以下函数时，不会执行finally
            //System.exit(1);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //在return之后会再执行finally
            logger.info("finally...");
            flag = true;
            return flag;
        }
    }
    /**
     * final, finally, finalize的区别
     */
    @Test
    public void finalFinallyFinalizeTest(){
        //final用于声明属性（属性不可变）、方法（方法不可覆盖）和类（类不可继承）
        //内部类要访问局部变量，局部变量必须定义成 final 类型，如下：
        final int num=0;
        class UserNum{
            public void addNum(){
                logger.info("{}",num);
            }
        }

        //finally是异常处理语句结构的一部分，表示总是执行

        //finalize是Object类的一个方法，垃圾收集器执行时会调用被回收对象的此方法
        //可覆盖此方法提供垃圾收集时的其他资源回收，eg.关闭文件等
        //JVM不保证此方法总被调用
    }
}
