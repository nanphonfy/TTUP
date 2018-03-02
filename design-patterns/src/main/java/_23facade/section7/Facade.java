package _23facade.section7;

/**
 * ������ģʽ�У������ɫӦ�����ȶ��ģ���Ӧ�����任��һ��ϵͳͶ�����оͲ�Ӧ���ı䡣
 * ��ҵ���߼��ᾭ���仯�����仯��װ����ϵͳ�ڲ���
 * ������α仯��������������˵��������ͬһ�����档
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Facade {
	//��ί�еĶ���
	private ClassA a = new ClassA();
	private ClassB b = new ClassB();
	private Context context = new Context();
	
	//�ṩ���ⲿ���ʵķ���
	public void methodA(){
		this.a.doSomethingA();
	}
	
	public void methodB(){
		this.b.doSomethingB();
	}

	/**
	 * �������ͨ���������ʣ����һ�����ӵ�ҵ���߼���
	 */
	public void methodC(){
		this.context.complexMethod();
	}
}
