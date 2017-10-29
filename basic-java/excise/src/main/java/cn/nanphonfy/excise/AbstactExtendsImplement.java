package cn.nanphonfy.excise;

interface IA {
    void run(String name);
}

/**
 * 接口可以继承接口
 */
interface IB extends IA {
    void takeTask();
}

class CA implements IB {
    @Override public void takeTask() {
    }

    @Override public void run(String name) {
    }
}

/**
 * 抽象类可以实现(implements)接口
 * 抽象类可以继承具体类
 * 抽象类中可以有静态的main方法
 */
abstract class CB extends CA implements IB {
    public static void main(String[] args) {
    }
}

/**
 * 接口（interface）是抽象类的变体。在接口中，所有方法都是抽象的
 */
public class AbstactExtendsImplement {
}
