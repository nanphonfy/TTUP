package _08simple_factory.section3;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ��ɫ���֣��������Ĳ�׼ȷ�����͵��
 */
public class YellowHuman implements Human {
	@Override public void getColor(){
		System.out.println("��ɫ���ֵ�Ƥ����ɫ�ǻ�ɫ�ģ�");
	}

	@Override public void talk() {
		System.out.println("��ɫ���ֻ�˵����һ��˵�Ķ���˫�ֽڡ�");
	}
}
