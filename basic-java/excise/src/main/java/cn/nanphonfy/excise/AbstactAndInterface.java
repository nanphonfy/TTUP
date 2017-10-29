package cn.nanphonfy.excise;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * abstract class和interface有什么区别
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/10/29
 */
public class AbstactAndInterface {
    /**
     * 区别：抽象类(A)VS接口(I)
     * ①构造方法：A有，I无；
     * ②普通成员变量：A有,I无；
     * ③非抽象普通方法：A可包含，I不能；
     * ④访问类型：A可为public、protected和默认，I只能是public abstract类型
     * ⑤静态方法：A可包含，I不能；
     * ⑥静态成员变量：A可任意类型，I只能是public static final；
     * ⑦一个类只能继承1个A，可实现多个I。
     */
    //    接口更多在系统架构设计方法发挥作用，主要用于定义模块之间的通信契约
    //    抽象类在代码实现方面发挥作用，可以实现代码的重用，eg.模板方法设计模式(在文末)
}

/**
 * 接口（interface）可以说成是抽象类的一种特例，接口中的所有方法都必须是抽象的。
 * 接口中的方法定义默认为public abstract类型，接口中的成员变量类型默认为public static final
 */
interface I1 {
    int i = 2;
    public static final int j = 2;

    void task0();

    public abstract void task();
}

/**
 * abstract类不能创建实例对象
 */
abstract class Father {
    public static final int MAX_VALUE = 999;
    //不能有抽象静态方法,因为抽象的方法是要被子类实现的，而static与子类扯不上关系
    //public abstract static void runError();

    //不能同时使用abstract和synchronized,ynchronized应作用在一个具体的方法上才有意义。
    // 且方法上的synchronized同步所使用的同步锁对象是this，而抽象方法上无法确定this是什么
    //public abstract synchronized void runError();

    //不能有抽象构造方法
    //abstract Father(int i){}

    Father() {
    }

    /**
     * 可包含静态方法
     *
     * @return
     */
    public static String getValue() {
        return "";
    }

    /**
     * 含有abstract方法的类必须定义为abstract class
     */
    public abstract void run();

    /**
     * abstract class类中的方法不必是抽象的
     */
    void take() {
    }
}

class Son extends Father {
    /**
     * 类中定义抽象方法必须在具体(Concrete)子类中实现
     */
    @Override public void run() {
    }
}

abstract class AbsSon extends Father {
    //若子类没实现抽象父类的所有抽象方法，那么子类也必须定义为abstract类
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/**
 * 利用抽象类实现模板方法的设计模式
 */
abstract class BaseServlet extends HttpServlet {
    @Override public final void service(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //记录访问日志进行权限判断
        if (true) {
            try {
                doService(request, response);
            } catch (Exception e) {
                //记录异常信息
            }
        }
    }

    /**
     * 访问权限定义成protected，显得既专业，又严谨，因为它是专门给子类用的
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    protected abstract void doService(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}

/**
 * 父类方法中间的某段代码不确定，留给子类干，就是模板方法设计模式
 */
class MyServlet1 extends BaseServlet {
    @Override protected void doService(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        //本Servlet只处理的具体业务逻辑代码
    }
}
