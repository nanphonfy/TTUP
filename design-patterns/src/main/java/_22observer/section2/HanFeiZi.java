package _22observer.section2;

/**
 * 通过聚集方式的被观察者
 * 引用LiSi实例，接口完全没修改，只修改实现类
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         韩非子，李斯的师弟，韩国的重要人物
 */
public class HanFeiZi implements IHanFeiZi {
    //把李斯声明出来
    private ILiSi liSi = new LiSi();

    //韩非子要吃饭了
    @Override public void haveBreakfast() {
        System.out.println("韩非子:开始吃饭了...");
        //通知李斯
        this.liSi.update("韩非子在吃饭");
    }

    //韩非子开始娱乐了,古代人没啥娱乐，你能想到的就那么多
    @Override public void haveFun() {
        System.out.println("韩非子:开始娱乐了...");
        this.liSi.update("韩非子在娱乐");
    }
}
