package _23facade.section4;

/**
 * ����ģʽҲ�����ģʽ����һ�ֱȽϳ��õķ�װģʽ��
 * ��˵�������������������ϵͳ�����ܴ���һ�����ʮ������ļ��ϣ��ڲ���Ψһͨ����������ϵͳ�ڲ���ô�������¡�
 * ������
 * ��Facade�����ɫ����ʵ��ҵ���߼���ֻ��ί���ࣻ
 * ��sybsystem��ϵͳ��ɫ������ļ��ϣ���֪������Ĵ��ڣ�����ֻ��һ���ͻ��˶��ѡ�
 *
 * ������ģʽ���ŵ㡿
 * �ټ���ϵͳ���໥������
 * �����ֱ��������ϵͳ�ڲ����໥ǿ��ϡ�
 * ����ģʽ�������������Ƕ�������������������ϵͳ�޹ء�
 * �����������ԣ�
 * �������٣��������ߡ�
 * ������ϵͳ�ڲ���α仯��ֻҪ��Ӱ����������������ɻ��
 * ����߰�ȫ�ԡ�
 * �������濪ͨ�ķ������޷����ʡ�
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Facade {
	//��ί�еĶ���
	//sybsystem��ϵͳ��ɫ��������������
	/**
	 * ��3�������ڽ��ڣ��������ҵ��һ����ϵͳ�Ĳ�ͬ�߼�����ģ�顣
	 * ���ڴ���ϵͳ���ʣ���ͨ�����档
	 */
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
	
	public void methodC(){
		this.c.doSomethingC();
	}
}
