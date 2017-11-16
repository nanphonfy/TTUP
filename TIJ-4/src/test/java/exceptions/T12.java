package exceptions;

import org.junit.Test;

import static exceptions.FullConstructors.f;
import static exceptions.FullConstructors.g;
import static exceptions.LoggingExceptions2.logException;

/**
 * 编译期间并不能找出所有错误，余下时间必须在运行期间解决。
 * java异常机制不仅节省代码，还把“描述在正常执行过程中做什么事”和“出了问题怎么办”的代码相分离，使代码的阅读、编写和调试工作更井井有条。
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/14
 */
public class T12 {
    /**
     * 【基本异常】
     * 异常情形：阻止当前方法或作用域继续执行的问题。
     * 抛出异常后，用new在堆上创建异常对象，终止当前执行路径，并从当前环境弹出对异常对象的引用。
     * 此时异常处理机制处理程序，将程序从错误状态中恢复，使程序要么换种方式运行，要么继续运行。
     */

    /**
     * new创建了异常对象后，将引用传给throw，然后退出方法或作用域。
     * 此外，能抛出任意类型的Throwable（异常类型的根类）对象
     */
    @Test
    public void exceptionTest() {
        String str=null;
        if(str == null){
            throw new NullPointerException();
        }
    }

    /**
     * 【捕获异常】
     监控区域：一段可能产生异常的代码，并且后面跟着处理这些异常的代码。
     try catch：抛出的异常必须在某处（异常处理程序）得到处理。
     终止与恢复：异常处理理论的两种基本模型：
     ①终止模型，假设错误非常关键，一旦异常被抛出，错误已无法返回，不能继续执行；
     ②恢复模型，修正错误，遇见错误时不抛异常，而是调用方法来修正该错误。
     */

    /**
     * 【创建自定义异常】
     * java提供的异常体系不可能预见所有错误，故可自定义异常类（必须从已有的异常类继承）
     */
    @Test
    public void InheritingExceptionsTest(){
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            /*Throw SimpleException from f()
            Caught it!*/
            System.out.println("Caught it!");
        }
    }

    /**
     * System.out会被重定向，System.err不会
     * 为异常类定义一个接受字符串参数的构造器
     */
    @Test
    public void FullConstructorsTest(){
        try {
            f();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
        try {
            g();
        } catch (MyException e) {
            e.printStackTrace(System.out);
        }
    }

    /**
     * 欲获取异常抛出处的栈轨迹，但printStackTrace默认不产生字符串，所以我们需要重载printStackTrace，接受PrintWriter对象做为参数
     */
    @Test
    public void LoggingExceptionsTest(){
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
    }

    /**
     * 更常见的是，需捕获和记录其他人编写的异常
     */
    @Test
    public void LoggingExceptions2Test(){
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }

    /**
     * 还可进一步自定义异常，加入额外的构造器和成员
     * 覆盖了Throwable.getMessage方法，以产生更详细的信息，有点类似toString
     */
    @Test
    public void MyException2Test(){

    }

    /**
     * 【异常说明】
     在编译时被强制检查的异常称为 被检查的异常。异常说明使用了附加关键字throws，后面接一个所有潜在异常类型的列表。

     【捕获所有异常】
     可以只写一个异常处理程序捕获所有类型的异常（最好放在处理程序的末尾，防止抢先把异常捕获了）
     */

    /**
     *
     */
    @Test
    public void Test(){

    }
}
