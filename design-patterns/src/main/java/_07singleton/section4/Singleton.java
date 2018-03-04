package _07singleton.section4;

/**
 * 懒汉模式
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public final class Singleton {
    private static Singleton singleton = null;

    //限制产生多个对象
    private Singleton() {
    }

    //通过该方法获得实例对象
    public synchronized static Singleton getSingleton() {
        /*线程A执行到singleton = new Singleton()，但还没获得对象，线程B也执行到singleton == null判断，
        线程A获得了一个对象，线程B也获得了一个对象，内存就出现两对象。*/
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
