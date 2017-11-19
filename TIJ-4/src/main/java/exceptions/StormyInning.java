package exceptions;
//: exceptions/StormyInning.java
// Overridden methods may throw only the exceptions
// specified in their base-class versions, or exceptions
// derived from the base-class exceptions.

class BaseballException extends Exception {
}

class Foul extends BaseballException {
}

class Strike extends BaseballException {
}

/**
 * 在Inning类，构造器和event都声明将抛出异常，但实际上没有抛（强制用户去捕获可能在覆盖后的event版本中增加的异常）
 */
abstract class Inning {
    public Inning() throws BaseballException {
    }

    public void event() throws BaseballException {
        // Doesn't actually have to throw anything
    }

    public abstract void atBat() throws Strike, Foul;

    public void walk() {
    } // Throws no checked exceptions
}

class StormException extends Exception {
}

class RainedOut extends StormException {
}

class PopFoul extends Foul {
}

/**
 * Storm接口，包含了一个在Inning定义的event和一个不在Inning定义的rainHard
 * 都抛出RainedOut异常
 */
interface Storm {
    void event() throws RainedOut;

    void rainHard() throws RainedOut;
}

/**
 * Storm里的event不能改变Inning中event的异常接口，在使用基类时才能判断是否捕获了正确异常
 * 若接口里定义的方法不是来自基类，eg.rainHard，那么抛出什么异常都没问题
 */
public class StormyInning extends Inning implements Storm {
    // OK to add new exceptions for constructors, but you
    // must deal with the base constructor exceptions:

    /**
     * 异常限制对构造器不起作用，
     * 不用理会基类构造器所抛出异常，然而派生类构造器（不能捕获基类构造器抛出的异常）必须包含基类构造器的异常说明
     * @throws RainedOut
     * @throws BaseballException
     */
    public StormyInning() throws RainedOut, BaseballException {
    }

    public StormyInning(String s) throws Foul, BaseballException {
    }

    // Regular methods must conform to base class:
    /**
     * 派生类实现方法可能会抛异常，而基类没抛，所以就失灵了
     * @throws PopFoul
     */
    //void walk() throws PopFoul {}//Compile error

    // Interface CANNOT add exceptions to existing
    // methods from the base class:
    //! public void event() throws RainedOut {}

    // If the method doesn't already exist in the
    // base class, the exception is OK:
    @Override public void rainHard() throws RainedOut {
    }

    // You can choose to not throw any exceptions,
    // even if the base version does:

    /**
     * 覆盖后的event，派生类可不抛出任何异常，即使基类有定义异常。
     */
    @Override public void event() {
    }

    // Overridden methods can throw inherited exceptions:

    /**
     * 基类throws Strike, Foul，而PopFoul又继承Foul
     * @throws PopFoul
     */
    @Override public void atBat() throws PopFoul {
    }

    /**
     * 不能基于异常说明来重载方法（基类的异常不一定会出现在派生类的方法的异常说明里，因为异常说明本身不属于方法类型的一部分），
     * 这点同继承的规则明显不同
     * @param args
     */
    public static void main(String[] args) {
        try {
            //编译器强制要求捕获这个类所抛出的异常
            StormyInning si = new StormyInning();
            si.atBat();
        } catch (PopFoul e) {
            System.out.println("Pop foul");
        } catch (RainedOut e) {
            System.out.println("Rained out");
        } catch (BaseballException e) {
            System.out.println("Generic baseball exception");
        }
        // Strike not thrown in derived version.
        try {
            // What happens if you upcast?
            //向上转型成基类,编译器会要求你捕获基类异常
            Inning i = new StormyInning();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch (Strike e) {
            System.out.println("Strike");
        } catch (Foul e) {
            System.out.println("Foul");
        } catch (RainedOut e) {
            System.out.println("Rained out");
        } catch (BaseballException e) {
            System.out.println("Generic baseball exception");
        }
    }
} ///:~
