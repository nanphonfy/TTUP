package cn.nanphonfy.excise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized VS java.util.concurrent.locks.Lock
 * 相同点：Lock能完成synchronized实现的所有功能
 * 不同点：Lock比synchronized更精确的线程语义和更好的性能。
 * synchronized 会自动释放锁，而 Lock一定要手工释放，且必须在 finally中释放。
 * Lock还有更强大的功能，eg.tryLock方法可以非阻塞方式去拿锁
 *
 * @author nanphonfy(南风zsr)
 * @date 2017/11/4
 */
public class SynchronizedLockThread {
    private int j;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedLockThread synchronizedLockThread = new SynchronizedLockThread();
        for (int i = 0; i < 2; i++) {
            new Thread(synchronizedLockThread.new Adder()).start();
            new Thread(synchronizedLockThread.new Subtractor()).start();
        }
    }

    private class Subtractor implements Runnable {
        @Override public void run() {
            while (true) {
                /*synchronized (ThreadTest.this) {
                    System.out.println("j--=" + j--);
                }*/
                lock.lock();
                try {
                    System.out.println("j--=" + j--);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Adder implements Runnable {
        @Override public void run() {
            while (true) {
                /*synchronized (ThreadTest.this) {
                    System.out.println("j++=" + j++);
                }*/
                lock.lock();
                try {
                    System.out.println("j++=" + j++);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
