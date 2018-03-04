package _07singleton.section4;

/**
 * ����ģʽ
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public final class Singleton {
    private static Singleton singleton = null;

    //���Ʋ����������
    private Singleton() {
    }

    //ͨ���÷������ʵ������
    public synchronized static Singleton getSingleton() {
        /*�߳�Aִ�е�singleton = new Singleton()������û��ö����߳�BҲִ�е�singleton == null�жϣ�
        �߳�A�����һ�������߳�BҲ�����һ�������ڴ�ͳ���������*/
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
