package _23facade.section1;

/**
 * ������
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class Client {

	/**
	 *����ھ������Զ������������ط��򡢽ӿڸ���ԭ���ˡ�
	 ��Ҫ֪����4���裻
	 ��˳����������ʼģ�
	 ��û�����һ����ĵ�һְ��
	 ���ż����ˣ�ÿ�ⶼҪ������תһ�顣
	 * @param args
     */
	public static void main(String[] args) {
		//����һ�������ż��Ĺ���
		ILetterProcess letterProcess = new LetterProcessImpl();	
		//��ʼд��
		letterProcess.writeContext("Hello,It's me,do you know who I am? I'm your old lover. I'd like to....");	
		//��ʼд�ŷ�
		letterProcess.fillEnvelope("Happy Road No. 666,God Province,Heaven");	
		//���ŷŵ��ŷ������װ��
		letterProcess.letterInotoEnvelope();	
		//�ܵ��ʾְ����������䣬Ͷ��
		letterProcess.sendLetter();
	}
}
