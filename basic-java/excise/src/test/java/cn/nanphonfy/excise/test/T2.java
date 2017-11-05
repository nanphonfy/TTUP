package cn.nanphonfy.excise.test;

import org.junit.Test;

import java.util.*;
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
    @Test
    public void ThreadTest() {
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

    /**
     * Map的四种遍历方法
     * 响应时间
     */
    @Test
    public void foreachMapTest() {
        Map<String, String> map = new HashMap<String, String>();
        for(int i=0;i<10;i++){
            map.put(""+i, "v"+i);
        }

        //第一种：普遍使用，二次取值
        System.out.println("通过Map.keySet遍历key和value：");
        long start = System.currentTimeMillis();
        for (String key : map.keySet()) {
            logger.info(String.format("key=%s,value=%s", key, map.get(key)));
        }
        //20个元素：34ms，，10个元素：16ms
        logger.info("{}",System.currentTimeMillis()-start);

        //第二种
        System.out.println("通过Map.entrySet使用iterator遍历key和value");
        start = System.currentTimeMillis();
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            logger.info(String.format("key=%s,value=%s", entry.getKey(), entry.getValue()));
        }
        //20个元素：7ms，10个元素：9ms
        logger.info("{}",System.currentTimeMillis()-start);

        //第三种：推荐，尤其是容量大时
        System.out.println("通过Map.entrySet遍历key和value");
        start = System.currentTimeMillis();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            logger.info(String.format("key=%s,value=%s", entry.getKey(), entry.getValue()));
        }
        //20个元素：14ms，10个元素：6ms
        logger.info("{}",System.currentTimeMillis()-start);

        //第四种
        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
        start = System.currentTimeMillis();
        for (String v : map.values()) {
            logger.info(String.format("value=%s", v));
        }
        //20个元素：7ms，10个元素：6ms
        logger.info("{}",System.currentTimeMillis()-start);
    }

    /**
     * 对数组去重
     */
    @Test
    public void noRepeatArrayListTest() {
        List<String> list=new ArrayList<>();
        list.add("Shenzhen");
        list.add("Shenzhen");

        //法1
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }
        logger.info("{}",newList.size());
        //法2
        HashSet hashSet=new HashSet(list);
        logger.info("{}",hashSet.size());
    }

    /**
     * 关于Set的非重复判断以及“==”和“equals”的区别
     * http://blog.csdn.net/cyzero/article/details/7261581
     * Set是Collection容器的一个子接口，它不允许出现重复元素，当然也只允许有一个null对象
     * ==是用来判断两者是否是同一对象（同一事物），而equals是用来判断是否引用同一个对象。
     * set里面存的是对象的引用，当两个元素只要满足了equals()[详见Object类]时就已经指向同一个对象也就出现了重复元素。
     */
    @Test
    public void noRepeatHashSetTest(){
        String str1 = new String("aaa");
        String str2 = "aaa";
        Set set = new HashSet();
        set.add(str1);
        set.add(str2);
        //str1 == str2:false，引用的地址不同
        logger.info("str1 == str2:{}", (str1 == str2));
        //str1.equals(str2):true，引用的地址不同但内容相同
        logger.info("str1.equals(str2):{}", str1.equals(str2));
        //in the set:[aaa]，因为HashSet的key是Object(或其子类)，
        //其equals方法就是对比引用指向的对象是否一样，若不一样，其内容是否一样（详见String覆盖的equals）
        logger.info("in the set:{} ", set);
    }


}
