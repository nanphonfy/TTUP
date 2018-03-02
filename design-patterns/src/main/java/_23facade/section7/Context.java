package _23facade.section7;

/**
 * 要建立一个封装类，将其提供给门面对象
 * 该封装类作用：产生一个业务规则，且生存环境在子系统内。
 * 门面对象通过对它访问，完成一个复杂的业务逻辑。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Context {
    //委托处理
    private ClassA a = new ClassA();
    private ClassC c = new ClassC();

    //复杂的计算
    public void complexMethod() {
        this.a.doSomethingA();
        this.c.doSomethingC();
    }
}
