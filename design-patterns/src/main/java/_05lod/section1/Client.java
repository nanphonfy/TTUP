package _05lod.section1;

/**
 * section1的故事：Teacher类发生命令给体育委员，让其清点人数。
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 *         我们使用Client来描绘一下这个场景
 */
public class Client {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();

        //老师发布命令
        teacher.commond(new GroupLeader());
    }

}
