package cn.nanphonfy.excise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 子线程循环10次，接着主线程循环5，如此循环50次
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/4
 */
public class MainAndSubThread {
    public static final int MAIN_TIMES = 5;
    public static final int SUB_TIMES = 10;
    public static final int TIMES = 50;

    public static void main(String[] args) {
        MainAndSubThread mainAndSubThread = new MainAndSubThread();
        mainAndSubThread.init();
    }

    public void init() {
        new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < TIMES; i++) {
                    subThread(i);
                }
            }
        }).start();
        for (int i = 0; i < TIMES; i++) {
            mainThread(i);
        }
    }

    /**
     * 定义了控制谁执行的信号灯
     */
    boolean bShouldSub = true;

    public synchronized void mainThread(int i) {
        if (bShouldSub) {
            try {
                this.wait();
                //MainAndSubThread.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < MAIN_TIMES; j++) {
            System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
        }
        bShouldSub = true;
        this.notify();
        //MainAndSubThread.class.notify();
    }

    public synchronized void subThread(int i) {
        if (!bShouldSub) {
            try {
                this.wait();
                //MainAndSubThread.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < SUB_TIMES; j++) {
            System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
        }
        bShouldSub = false;
        this.notify();
        //MainAndSubThread.class.notify();
    }
}

class MainAndSubLockThread {
    public static final int MAIN_TIMES = 5;
    public static final int SUB_TIMES = 10;
    public static final int TIMES = 50;

    private static Lock lock = new ReentrantLock();
    private static Condition subThreadCondition = lock.newCondition();
    /**
     * 定义了控制谁执行的信号灯
     */
    private static boolean bShouldSubThread = true;

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        threadPool.execute(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < TIMES; i++) {
                    lock.lock();
                    try {
                        if (!bShouldSubThread) {
                            subThreadCondition.await();
                        }
                        for (int j = 0; j < SUB_TIMES; j++) {
                            System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
                        }
                        bShouldSubThread = false;
                        subThreadCondition.signal();
                    } catch (Exception e) {
                    } finally {
                        lock.unlock();
                    }
                }
            }
        });
        threadPool.shutdown();

        for (int i = 0; i < TIMES; i++) {
            lock.lock();
            try {
                if (bShouldSubThread) {
                    subThreadCondition.await();
                }
                for (int j = 0; j < MAIN_TIMES; j++) {
                    System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
                }
                bShouldSubThread = true;
                subThreadCondition.signal();
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        }
    }
}
