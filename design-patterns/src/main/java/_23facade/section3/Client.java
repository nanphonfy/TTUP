package _23facade.section3;

/**
 * ����ģʽҲ�����ģʽ����һ�ֱȽϳ��õķ�װģʽ��
 * ��˵�������������������ϵͳ�����ܴ���һ�����ʮ������ļ��ϣ��ڲ���Ψһͨ����������ϵͳ�ڲ���ô�������¡�
 * ������
 * ��Facade�����ɫ����ʵ��ҵ���߼���ֻ��ί���ࣻ
 * ��sybsystem��ϵͳ��ɫ������ļ��ϣ���֪������Ĵ��ڣ�����ֻ��һ���ͻ��˶��ѡ�
 *
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

