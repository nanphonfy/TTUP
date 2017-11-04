package cn.nanphonfy.excise.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author nanphonfy(南风zsr)
 * @date 2017/11/04.
 */
public class T2 extends AbstractTest {
    /**
     * 有几种方法可实现一个线程
     */
    @Test public void ThreadTest() {
        //①Thread的匿名子类的实例对象
        new Thread() {
            @Override public void run() {
            }
        }.start();
        //②Runnable的匿名子类的实例对象
        new Thread(new Runnable() {
            @Override public void run() {
            }
        }).start();
        //③线程池创建多线程
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override public void run() {
                }
            });
        }
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override public void run() {
            }
        });
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override public void run() {
            }
        });
    }
}
