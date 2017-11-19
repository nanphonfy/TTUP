package exceptions;

import org.junit.Test;

import static exceptions.FullConstructors.f;
import static exceptions.FullConstructors.g;
import static exceptions.LoggingExceptions2.logException;
import static net.mindview.util.Print.print;

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

     Exception（Exception extends Throwable）是所有异常类的基类，获取详细信息：getMessage()、getLocalizedMessage()、toString()

     打印Throwable和其调用栈（带到抛出地点）轨迹：printStackTrace()、printStackTrace(PrintStream s)、void printStackTrace(PrintStreamOrWriter s)

     在Throwable对象的内部记录栈帧的当前状态：Throwable fillInStackTrace()

     也可使用Throwable从基类Object继承的方法：getClass、getName、getSimpleName
     */

    /**
     * 每个方法都比前一个提供更多信息（每一个都是前一个的超集）
     */
    @Test
    public void ExceptionMethodsTest(){
        /**
         * Caught Exception
         getMessage():My Exception
         getLocalizedMessage():My Exception
         toString():java.lang.Exception: My Exception
         printStackTrace():
         java.lang.Exception: My Exception
         at exceptions.T12.ExceptionMethodsTest(T12.java:140)
         */
        /*try {
            throw new Exception("My Exception");
        } catch (Exception e) {
            print("Caught Exception");
            print("getMessage():" + e.getMessage());
            print("getLocalizedMessage():" + e.getLocalizedMessage());
            print("toString():" + e);
            print("printStackTrace():");
            e.printStackTrace(System.out);
        }*/
    }

    /**
     * 【栈轨迹】
     * printStackTrace提供的信息，可通过getStackTrace访问（栈轨迹中的元素构成的数组，每个元素都是栈中的一帧）
     * 只打印了方法名，还可打印整个StackTraceElement
     */
    @Test
    public void WhoCalledTest(){
        /*f(exceptions.WhoCalled.f(WhoCalled.java:9))
        main(exceptions.WhoCalled.main(WhoCalled.java:27))
        invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
        invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
        invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
        invoke(java.lang.reflect.Method.invoke(Method.java:497))
        main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))
        --------------------------------
        f(exceptions.WhoCalled.f(WhoCalled.java:9))
        g(exceptions.WhoCalled.g(WhoCalled.java:19))
        main(exceptions.WhoCalled.main(WhoCalled.java:29))
        invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
        invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
        invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
        invoke(java.lang.reflect.Method.invoke(Method.java:497))
        main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))
        --------------------------------
        f(exceptions.WhoCalled.f(WhoCalled.java:9))
        g(exceptions.WhoCalled.g(WhoCalled.java:19))
        h(exceptions.WhoCalled.h(WhoCalled.java:23))
        main(exceptions.WhoCalled.main(WhoCalled.java:31))
        invoke0(sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method))
        invoke(sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62))
        invoke(sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43))
        invoke(java.lang.reflect.Method.invoke(Method.java:497))
        main(com.intellij.rt.execution.application.AppMain.main(AppMain.java:144))*/
    }

    /**
     * 【重新抛出异常】
     * 把刚捕获的异常重新抛出，高一级环境可以得到这个异常对象的所有信息，
     * catch (Exception e) {
     * System.out.println("...");
     * throw e;
     * }
     * printStackTrace并非重新抛出点的信息
     * fillInStackTrace会更新信息，高一级捕获到该对象
     */
    @Test
    public void RethrowingTest(){

    }

    /**
     * 在捕获异常后抛出另外一种异常，将得到类似fillInStackTrace的效果
     * 有关原来异常发生点信息丢失，剩下与新抛出点有关信息
     */
    @Test
    public void RethrowNewTest(){

    }

    /**
     * Throwable子类中，提供带cause参数的构造器的有：Error、Exception、RuntimeException。
     * 其他类型的异常要链接起来，应该使用initCause而不是构造器
     * getField(String id) throws NoSuchFieldException
     */
    @Test
    public void DynamicFieldsTest(){
        /*try {
            //从setField方法内抛出，视为编程错误，把NoSuchFieldException转成RuntimeException抛出
            result = getField(id); // Get old value
        } catch (NoSuchFieldException e) {
            // Use constructor that takes "cause":
            throw new RuntimeException(e);
        }*/
    }

    /**
     * 【java标准异常】
     Throwable对象可分为两种类型（指继承Throwable的类型）：
     Error（编译时和系统错误，特殊情况下才关心）和Exception（java类库、用户方法、运行时故障都可能会抛出，程序员所关心的）
     */

    /**
     * "RuntimeException"
     * 运行时异常（继承RuntimeException类），会自动被JVM抛出。
     * 构成了一组具有相同特征和行为的异常类型，不需抛出RuntimeException的类型（不受检查异常，将自动捕获，无需亲自动手）
     * 但还可在代码中手动抛出RuntimeException类型的异常
     */
    @Test
    public void NeverCaughtTest(){
        /**
         * 如果RuntimeException没被捕获，直达main，那程序退出前将调用异常的printStackTrace方法
         * RuntimeException代表编程错误：①无法预料的错误（eg.null引用）；②程序员应在代码检查错误。
         */
        /*Exception in thread "main" java.lang.RuntimeException: From f()
        at exceptions.NeverCaught.f(NeverCaught.java:8)
        at exceptions.NeverCaught.g(NeverCaught.java:12)
        at exceptions.NeverCaught.main(NeverCaught.java:16)*/
    }

    /**
     * 【使用finally清理】
     * 对于一些代码，希望无论try块的异常是否抛出，都能得到执行
     * 末尾加上finally子句，无论异常是否抛出，总能运行
     */
    @Test
    public void FinallyWorksTest(){
        int count=0;
        while (true) {
            try {
                // Post-increment is zero first time:
                if (count++ == 0) {
                    throw new ThreeException();
                }
                System.out.println("No exception");
            } catch (ThreeException e) {
                System.out.println("ThreeException");
            } finally {
                System.out.println("In finally clause");
                if (count == 2) {
                    break; // out of "while"
                }
            }
        }
    }

    /**
     * 【finally用来做什么】
     * 当要把除内存外的资源恢复到他们的初始状态时，就要用finally子句（eg.已打开文件或网络连接）
     * 目的：确保main结束时开关必须为关闭（当异常被抛出，但没被处理程序捕获，这时sw.off就得不到调用）
     */
    @Test
    public void OnOffSwitchTest(){
        /*try {
            sw.on();
            // Code that can throw exceptions...
            f();
            sw.off();
        } catch (OnOffException1 e) {
            System.out.println("OnOffException1");
            sw.off();
        } catch (OnOffException2 e) {
            System.out.println("OnOffException2");
            sw.off();
        }*/
    }

    /**
     * 使用finally，能够确保在任何情况下都能得到执行
     */
    @Test
    public void WithFinallyTest(){
        /*try {
            sw.on();
            // Code that can throw exceptions...
            OnOffSwitch.f();
        } catch (OnOffException1 e) {
            System.out.println("OnOffException1");
        } catch (OnOffException2 e) {
            System.out.println("OnOffException2");
        } finally {
            sw.off();
        }*/
    }

    /**
     * 在异常没被当前异常处理程序捕获下，会在跳到更高一层的异常处理程序之前，执行finally子句
     * 当涉及break和continue时，finally子句也会得到执行
     */
    @Test
    public void AlwaysFinallyTest(){
        /*Entering first try block
        Entering second try block
        finally in 2nd try block
        Caught FourException in 1st try block
        finally in 1st try block*/
        print("Entering first try block");
        try {
            print("Entering second try block");
            try {
                throw new FourException();
            } finally {
                print("finally in 2nd try block");
            }
        } catch (FourException e) {
            System.out.println("Caught FourException in 1st try block");
        } finally {
            System.out.println("finally in 1st try block");
        }
    }

    /**
     * 【在return中使用finally】
     * finally总会被执行，可保证重要的清理工作仍旧会执行
     */
    @Test
    public void MultipleReturnsTest(){

    }

    /**
     * 【遗憾：异常丢失】
     * java异常实现也有瑕疵，它还是有可能被轻易忽略
     * VeryImportantException不见了，被finally的HoHumException所取代（相当严重的缺陷）
     */
    @Test
    public void LostMessageTest(){

    }

    /**
     * 即使抛出异常，也不会有任何输出
     */
    @Test
    public void ExceptionSilencerTest(){
        try {
            throw new RuntimeException();
        } finally {
            // Using 'return' inside the finally block
            // will silence any thrown exception.
            return;
        }
    }

    /**
     * 【异常的限制】
     * 在Inning类，构造器和event都声明将抛出异常，但实际上没有抛（强制用户去捕获可能在覆盖后的event版本中增加的异常）
     *
     * 不能基于异常说明来重载方法（基类的异常不一定会出现在派生类的方法的异常说明里，因为异常说明本身不属于方法类型的一部分），
     * 这点同继承的规则明显不同
     */
    @Test
    public void StormyInningTest(){

    }

    /**
     * 【构造器】
     * 构造器会把对象设置成安全初始状态（eg.打开一个文件，只有对象使用完且调用特殊清理方法后才能被清理）
     * 在构造器内抛出异常，这些清理工作也许就不能正常工作（也许该对象某些部分还没被成功创建，而finally却要被清理），意味着在编写构造器时要格外细心。
     */
    @Test
    public void InputFileTest(){

    }

    /**
     * 构造阶段可能抛异常且要求清理，最安全的方式：使用嵌套try子句
     */
    @Test
    public void CleanupTest(){
        try {
            InputFile in = new InputFile("Cleanup.java");
            try {
                String s;
                int i = 1;
                while ((s = in.getLine()) != null) {
                    ; // Perform line-by-line processing here...
                }
            } catch (Exception e) {
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.out);
            } finally {
                in.dispose();
            }
        } catch (Exception e) {
            System.out.println("InputFile construction failed");
        }
    }

    /**
     * 为了构造和清理，可把不能失败的构造器的对象群组在一起
     * 如何处理那些具有可失败的构造器，且需清理的对象
     * 对每个构造，都必须包含在其自己的try-finally语句块中，且每个对象构造都必须跟随一个try-finally语句块确保清理
     */
    @Test
    public void CleanupIdiomTest(){

    }

    /**
     *
     */
    @Test
    public void Test(){

    }
}
