package _05lod.section3;

/**
 * 软件安全过程，导向动作：①确认是否安全；②确认许可证；③选择安全目录……
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         业务场景
 */
public class Client {
    public static void main(String[] args) {
        InstallSoftware invoker = new InstallSoftware();
        invoker.installWizard(new Wizard());
    }
}
