package _12proxy.section6;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ��Ϸ���
 */
public interface IGamePlayer {
	//��¼��Ϸ
	void login(String user, String password);
	
	//ɱ�֣�����������Ϸ����Ҫ��ɫ
	void killBoss();
	
	//����
	void upgrade();
	
	//ÿ���˶�������һ���Լ��Ĵ���
	IGamePlayer getProxy();
}
