package _08simple_factory.section3;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ��ɫ������
 */
public class WhiteHuman implements Human {
	//��ɫ���ֵ���ɫ�ǰ�ɫ��
	@Override public void getColor(){
		System.out.println("��ɫ���ֵ�Ƥ����ɫ�ǰ�ɫ�ģ�");
	}
	
	//��ɫ���ֽ���
	@Override public void talk() {
		System.out.println("��ɫ���ֻ�˵����һ�㶼�ǵ��ǵ��ֽڡ�");
	}
}