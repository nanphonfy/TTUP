package _22observer.section1;

/**
 * 讲一个间谍的故事
 * 李斯（监控者）安排间谍，监控韩非子（被监控者）。
 * 创建一个后台线程一直运行，一旦发现韩非子动态，就触发事件上报给李斯。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         这个Client就是我们，用我们的视角看待这段历史
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        //定义出韩非子和李斯
        LiSi liSi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();

        //观察早餐
        Spy watchBreakfast = new Spy(hanFeiZi, liSi, "breakfast");
        //开始启动线程，监控
        watchBreakfast.start();

        //观察娱乐情况
        Spy watchFun = new Spy(hanFeiZi, liSi, "fun");
        watchFun.start();

        //然后这里我们看看韩非子在干什么
        Thread.sleep(1000); //主线程等待1秒后后再往下执行
        hanFeiZi.haveBreakfast();

        //韩非子娱乐了
        Thread.sleep(1000);
        hanFeiZi.haveFun();
    }
}
