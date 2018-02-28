package strings;

import net.mindview.util.Hex;
import org.junit.Test;

/**
 * 字符串操作是计算机程序设计中最常见的行为。
 * @author nanphonfy(南风zsr)
 * @date 2018/2/28
 */
public class T13 {
    /**
     * 【不可变String】
     * 每当把String对象做为方法参数时，都会复制一份引用。
     * 而引用本身的对象，一直待在单一物理位置上。
     */
    @Test
    public void ImmutableTest() {
        Immutable immutable = new Immutable();
    }

    /**
     * 【重载"+"与StringBuilder】
     * String对象是不可变的，可给一个String对象加任意多的别名。
     * 不可变性会带来一定效率问题，String的"+"和"+="是java仅有的两个重载过的操作符。
     * 编译器自动引入了StringBuilder类，用以构造最终的String，并为每个字符串调用一次StringBuilder的append方法，一共四次。
     * （编译器自动优化性能）
     */
    @Test
    public void ConcatenationTest() {
        Concatenation concatenation = new Concatenation();
    }

    /**
     * 【重载"+"与StringBuilder】
     * 两种方式生成一个String。①使用了多个String对象；②使用了StringBuilder。
     * 注意：StringBuilder在循环内构造，每循环一次，就会创建一个新的StringBuilder对象。
     * 循环部分代码更简短、更简单，且只生成一个StringBuilder对象。
     * 如果字符串操作较简单，可信赖编译器；如果要在toString方法中使用循环，最好自己创建一个StringBuilder对象，用来构造最终结果。
     */
    @Test
    public void WhitherStringBuilderTest() {
        WhitherStringBuilder whitherStringBuilder = new WhitherStringBuilder();
    }

    /**
     * 【重载"+"与StringBuilder】
     * 最终结果是用append一点点拼接起来的，若想走捷径（eg.append(a+":"+c)，编译器则会调入陷阱，另外创建一个StringBuilder）。
     * StringBuilder是SE5引入，在这之前java用的是StringBuffer（线程安全，开销较大）。
     */
    @Test
    public void UsingStringBuilderTest() {
        UsingStringBuilder usingStringBuilder = new UsingStringBuilder();
    }

    /**
     * 【无意识的递归】
     * java每个类从根本上都是继承Object，容器类液也有toString。
     * eg.ArrayList.toString()会遍历ArrayList包含的所有对象，调用每个元素上的toString方法。
     */
    @Test
    public void ArrayListDisplayTest() {
        ArrayListDisplay arrayListDisplay = new ArrayListDisplay();
    }

    /**
     * 【递归调用】
     * 若希望toString打印出对象内存地址，考虑this关键字（可能发生自动类型转换,而发生递归陷阱）。
     * 编译器看到一个String对象后跟着一个"+",而后面的对象不是String，
     * 于是编译器试图将this转换成一个String（调用this上的toString，递归调用）
     * 若真想打印出对象的内存地址，应该调用Object.toString，不该使用this，而使用super.toString。
     */
    @Test
    public void InfiniteRecursionTest() {
        InfiniteRecursion infiniteRecursion = new InfiniteRecursion();
    }

    /**
     * 【格式化输出】
     * format与printf等价（%d %f等占位符称作格式修饰符）
     * 【Formatter类】
     * Formatter的构造器经过重载可以接受多种输出目的地，不过最常用的还是PrintStream、OutputStream和File
     */
    @Test
    public void SimpleFormatTest() {
        SimpleFormat simpleFormat = new SimpleFormat();
    }

    /**
     * 【格式化说明符】
     * 通过相当简洁的语法，Formatter提供了对空格与对齐的强大控制能力。
     */
    @Test
    public void ReceiptTest() {

    }

    /**
     * 【Formatter转换】
     * d 整数型
     * c Unicode字符
     * b boolean值
     * s String
     * f 浮点数（十进制）
     * e 浮点数（科学计数）
     * x 整数（十六进制）
     * h 散列码（十六进制）
     * % 字符%
     */
    @Test
    public void ConversionTest() {
        Conversion conversion = new Conversion();
    }

    /**
     * 【String.format】
     * String.format是static方法
     * 在String.format的内部，也是创建一个Formatter对象
     */
    @Test
    public void DatabaseExceptionTest() {
        DatabaseException databaseException = new DatabaseException(1,1,"");
    }

    /**
     * 【十六进制dump工具】
     * 处理二进制文件时，希望以十六进制格式看内容。
     */
    @Test
    public void HexTest() {
        Hex hex = new Hex();
    }

    /**
     *
     */
    @Test
    public void Test() {

    }
}
