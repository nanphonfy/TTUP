package _23facade.section2;

/**
 * ֻҪ�� �����������ģ����衢˳��ȣ����ù��ġ��ȼ򵥣�������չ�ԡ�
 * eg.��ĳ������ʱ�ڣ���Ҫ���ʼ����а�ȫ��顣
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class Client {
	public static void main(String[] args) {
		//�ִ������ʾ֣�����������ʾ����ƽ�Hell Road
		ModenPostOffice hellRoadPostOffice = new ModenPostOffice();
		//��ֻҪ���ŵ����ݺ������˵�ַ����������������һϵ�еĹ�����
		//����һ����ַ
		String address = "Happy Road No. 666,God Province,Heaven";
		//�ŵ�����
		String context = "Hello,It's me,do you know who I am? I'm your old lover. I'd like to....";
		//����ҷ��Ͱ�
		hellRoadPostOffice.sendLetter(context, address);
	}
}

