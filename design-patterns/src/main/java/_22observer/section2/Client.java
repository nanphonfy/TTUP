package _22observer.section2;

/**
 * 通过聚集方式的被观察者
 * 引用LiSi实例，接口完全没修改，只修改实现类
 *
 * 通过聚集方式非常简单，但有问题。
 * 战国不止李斯在监视韩非子，且观察活动很广，eg.政治倾向、思维倾向、朝野活动等。
 * 聚集方式和开闭原则严重违背。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         这个Client就是我们，用我们的视角看待这段历史
 */
public class Client {

    public static void main(String[] args) {
        //定义出韩非子
        HanFeiZi hanFeiZi = new HanFeiZi();

        //然后这里我们看看韩非子在干什么
        hanFeiZi.haveBreakfast();

        //韩非子娱乐了
        hanFeiZi.haveFun();

    }
}
