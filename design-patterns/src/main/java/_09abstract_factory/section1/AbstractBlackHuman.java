package _09abstract_factory.section1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public abstract class AbstractBlackHuman implements Human {

	@Override public void getColor(){
		System.out.println("��ɫ���ֵ�Ƥ����ɫ�Ǻ�ɫ�ģ�");
	}

	@Override public void talk() {
		System.out.println("���˻�˵����һ������������");

	}

}
