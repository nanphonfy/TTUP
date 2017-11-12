//: innerclasses/controller/Event.java
// The common methods for any control event.
package innerclasses.controller;

/**
 * 接口描述了要控制的事件，因为其默认行为是基于时间去执行控制，所以使用抽象类代替实际接口
 */
public abstract class Event {
    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime) {
        this.delayTime = delayTime;
        start();
    }

    public void start() { // Allows restarting
        eventTime = System.nanoTime() + delayTime;
    }

    /**
     * 就绪的时候，执行事件
     *
     * @return
     */
    public boolean ready() {
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
} ///:~
