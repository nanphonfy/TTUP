package _23facade.section1;

/**
 * д�Ź��̽ӿ�
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public interface ILetterProcess {
	//����Ҫд�ŵ�����
	void writeContext(String context);
	//���д�ŷ�
	void fillEnvelope(String address);
	//���ŷŵ��ŷ���
	void letterInotoEnvelope();
	//Ȼ���ʵ�
	void sendLetter();
}
