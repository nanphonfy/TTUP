package _23facade.section2;

/**
 * ֻҪ�� �����������ģ����衢˳��ȣ����ù��ġ��ȼ򵥣�������չ�ԡ�
 * eg.��ĳ������ʱ�ڣ���Ҫ���ʼ����а�ȫ��顣
 *
 * @author cbf4Life cbf4life@126.com
 *         I'm glad to share my knowledge with you all.
 */
public class LetterProcessImpl implements ILetterProcess {
	//д��
	@Override public void writeContext(String context) {
		System.out.println("��д�ŵ�����...." + context);
	}
	//���ŷ�����д��Ҫ����Ϣ
	@Override public void fillEnvelope(String address) {
		System.out.println("��д�ռ��˵�ַ������...." + address);
	}
	//���ŷŵ��ŷ��У������
	@Override public void letterInotoEnvelope() {
		System.out.println("���ŷŵ��ŷ���....");
	}
	//���������У��ʵ�
	@Override public void sendLetter() {
		System.out.println("�ʵ��ż�...");
	}
}
