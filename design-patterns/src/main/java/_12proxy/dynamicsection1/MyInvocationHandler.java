package _12proxy.dynamicsection1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class MyInvocationHandler implements InvocationHandler {
    //�������Ķ���
    private Object target = null;

    //ͨ�����캯������һ������
    public MyInvocationHandler(Object _obj) {
        this.target = _obj;
    }

    //��������
    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //���÷���ֵ
        Object result = null;
        //ǰ��֪ͨ
        this.before();
        //ִ�б������ķ���
        result = method.invoke(this.target, args);
        //����֪ͨ
        this.after();

        //����ֵ
        return result;
    }

    //ǰ��֪ͨ
    public void before() {
        System.out.println("ִ��before����");
    }

    //����֪ͨ
    public void after() {
        System.out.println("ִ��after����");
    }

}