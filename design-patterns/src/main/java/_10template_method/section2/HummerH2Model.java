package _10template_method.section2;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class HummerH2Model extends HummerModel {
	//H2�ͺŵĺ�������
	@Override public void alarm() {
		System.out.println("����H2����...");
	}

	//���������
	@Override public void engineBoom() {
		System.out.println("����H2����������������...");
	}

	//��������
	@Override public void start() {
		System.out.println("����H2����...");
	}
	
	//ͣ��
	@Override public void stop() {
		System.out.println("����H2ͣ��...");
	}
}
