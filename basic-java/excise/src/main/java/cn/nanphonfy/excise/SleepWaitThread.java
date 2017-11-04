package cn.nanphonfy.excise;

/**
 * sleep是线程类（Thread）的方法，wait 是 Object 类的方法.
 * sleep导致此线程暂停执行(主动让出cpu)，给其他线程执行的机会，但监控状态依然保持，到时间后会自动恢复。
 * 调用sleep不会释放对象锁（若当前线程进入同步锁，sleep并不会释放锁，其他被同步锁挡住了的线程也无法得到执行）
 * wait对此对象调用，导致本线程放弃对象锁，进入等待此对象的等待锁定池，
 * 只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态
 * wait指在一个已经进入同步锁的线程内，自己暂时让出同步锁，便其他正在等待此锁的线程可以得到同步锁并运行，
 * 只有其他线程调用了notify 方法（不释放锁，只是告诉调用过wait方法的线程可参与获得锁的竞争了，但不马上得到锁，
 * 因为锁还在别人手里，没释放。如果 notify 方法后面的代码还有很多，需要这些代码执行完后才会释放锁
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/4
 */
public class SleepWaitThread {
    public static void main(String[] args) {
        //        enter thread1...
        //        thread1 is waiting
        //        enter thread2...
        //        thread2 notify other thread can release wait status..
        //        thread2 is sleeping ten millisecond...
        //        thread2 is going on...
        //        thread2 is being over!
        //        thread1 is going on...
        //        thread1 is being over!
        new Thread(new Thread1()).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new Thread2()).start();
    }

    private static class Thread1 implements Runnable {
        @Override public void run() {
            /**
             * 由于Thread1和Thread2内部run方法要用同一对象作为监视器，在这里不能用this
             * 因为在Thread2里面的this和Thread1的this不是同一个对象
             * 我们用MultiThread.class，当前虚拟机里引用这个变量时，指向的都是同一个对象
             */
            synchronized (SleepWaitThread.class) {
                System.out.println("enter thread1...");
                System.out.println("thread1 is waiting");
                /**
                 * 释放锁有两种方式:
                 * ①程序自然离开监视器的范围，即离开synchronized 关键字管辖的代码范围
                 * ②在synchronized关键字管辖的代码内部调用监视器对象的 wait 方法。这里，使用 wait 方法释放锁。
                 */
                try {
                    SleepWaitThread.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread1 is going on...");
                System.out.println("thread1 is being over!");
            }
        }
    }

    private static class Thread2 implements Runnable {
        @Override public void run() {
            synchronized (SleepWaitThread.class) {
                System.out.println("enter thread2...");
                System.out.println("thread2 notify other thread can release wait status..");
                /**
                 * 由于notify方法并不释放锁，即使thread2调用下面的sleep方法休息了10毫秒，
                 * 但thread1仍不会执行，因为thread2没释放锁，Thread1无法得到锁。
                 */
                SleepWaitThread.class.notify();
                System.out.println("thread2 is sleeping ten millisecond...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread2 is going on...");
                System.out.println("thread2 is being over!");
            }
        }
    }
}
