package _23facade.section7;

/**
 * Ҫ����һ����װ�࣬�����ṩ���������
 * �÷�װ�����ã�����һ��ҵ����������滷������ϵͳ�ڡ�
 * �������ͨ���������ʣ����һ�����ӵ�ҵ���߼���
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Context {
    //ί�д���
    private ClassA a = new ClassA();
    private ClassC c = new ClassC();

    //���ӵļ���
    public void complexMethod() {
        this.a.doSomethingA();
        this.c.doSomethingC();
    }
}
