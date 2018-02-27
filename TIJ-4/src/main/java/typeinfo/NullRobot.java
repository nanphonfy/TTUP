package typeinfo;//: typeinfo/NullRobot.java
// Using a dynamic proxy to create a Null Object.
import net.mindview.util.Null;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

class NullRobotProxyHandler implements InvocationHandler {
    private String nullName;
    private Robot proxied = new NRobot();

    NullRobotProxyHandler(Class<? extends Robot> type) {
        nullName = type.getSimpleName() + " NullRobot";
    }

    private class NRobot implements Null, Robot {
        @Override
        public String name() {
            return nullName;
        }

        @Override
        public String model() {
            return nullName;
        }

        @Override
        public List<Operation> operations() {
            return Collections.emptyList();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}

public class NullRobot {
    /**
     * 无论何时，需一个空Robot对象，只需调用newNullRobot，并传递需要代理的Robot类型。
     * 代理会满足Robot和Null接口的需求，并提供所代理的类型的确切名字。
     *
     * @param type
     * @return
     */
    public static Robot newNullRobot(Class<? extends Robot> type) {
        return (Robot) Proxy.newProxyInstance(NullRobot.class.getClassLoader(), new Class[] { Null.class, Robot.class },
                new NullRobotProxyHandler(type));
    }

    public static void main(String[] args) {
        Robot[] bots = { new SnowRemovalRobot("SnowBee"), newNullRobot(SnowRemovalRobot.class) };
        for (Robot bot : bots) {
            Robot.Test.test(bot);
        }
    }
}
/* Output:
Robot name: SnowBee
Robot model: SnowBot Series 11
SnowBee can shovel snow
SnowBee shoveling snow
SnowBee can chip ice
SnowBee chipping ice
SnowBee can clear the roof
SnowBee clearing roof
[Null Robot]
Robot name: SnowRemovalRobot NullRobot
Robot model: SnowRemovalRobot NullRobot
*///:~
