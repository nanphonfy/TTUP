package typeinfo;//: typeinfo/SimpleDynamicProxy.java

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + ", method: " + method + ", args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println("  " + arg);
            }
        }
        return method.invoke(proxied, args);
    }
}

class SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        /**
         * 调用静态方法Proxy.newProxyInstance(）可创建动态代理，需要一个类加载器&一个该代理实现的接口列表（不是类或抽象类），
         * 以及InvocationHandler接口的一个实现。
         * 动态代理可将所有调用重定向到调用处理器。向调用处理器的构造器传递一个实际对象的引用，执行中介任务时，请求转发。
           invoke传递进代理对象，对接口的调用将重定向为对代理的调用。
         */
        Interface proxy = (Interface) Proxy
                .newProxyInstance(Interface.class.getClassLoader(), new Class[] { Interface.class },
                        new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
/* Output: (95% match)
doSomething
somethingElse bonobo
**** proxy: class $Proxy0, method: public abstract void Interface.doSomething(), args: null
doSomething
**** proxy: class $Proxy0, method: public abstract void Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@42e816
  bonobo
somethingElse bonobo
*///:~
