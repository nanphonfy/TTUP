package _23facade.section6;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Facade {
	//��ί�еĶ���
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private ClassC c = new ClassC();
	
	//�ṩ���ⲿ���ʵķ���
	public void methodA(){
		this.a.doSomethingA();
	}
	
	public void methodB(){
		this.b.doSomethingB();
	}

	/**
	 * �����治������ϵͳ�ڵ�ҵ���߼���
	 * ֻ��methodC������a.doSomethingA��Ȼ����ִ��c.doSomethingC��
	 * ����Ʋ����ף���Ϊ�������ֻ�ṩһ��������ϵͳ��·�����ѣ����ò�������ҵ���߼���
	 * �����������������⣺��ϵͳ��������������ܱ����ʡ���Υ����һְ��Ҳ�ƻ��˷�װ�ԣ�
	 */
	public void methodC(){
		this.a.doSomethingA();
		this.c.doSomethingC();
	}
}
