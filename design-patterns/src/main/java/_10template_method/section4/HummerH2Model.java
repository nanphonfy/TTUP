package _10template_method.section4;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * H1��H2��ʲô��𣬻��治֪������û�Ӵ�������
 */
public class HummerH2Model extends HummerModel {
	@Override protected void alarm() {
		System.out.println("����H2����...");
	}

	@Override protected void engineBoom() {
		System.out.println("����H2����������������...");
	}
	
	@Override protected void start() {
		System.out.println("����H2����...");
	}

	@Override protected void stop() {
		System.out.println("����H2ͣ��...");
	}
	
	//Ĭ��û�����ȵ�
	@Override protected boolean isAlarm() {
		return false;
	}
}
