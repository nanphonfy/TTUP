package _08simple_factory.section1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ��ɫ���֣��ǵ���ѧѧӢ���ʦ˵black man�������˵���˼��������û������˵��
 */
public class BlackHuman implements Human {
	@Override
	public void getColor(){
		System.out.println("��ɫ���ֵ�Ƥ����ɫ�Ǻ�ɫ�ģ�");
	}

	@Override
	public void talk() {
		System.out.println("���˻�˵����һ������������");
	}
}
