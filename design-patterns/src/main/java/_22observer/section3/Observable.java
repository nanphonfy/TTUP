package _22observer.section3;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * ���б��۲����ߣ�ͨ�ýӿ�
 */
public interface Observable {
	//����һ���۲���
	void addObserver(Observer observer);
	
	//ɾ��һ���۲��ߣ������Ҳ������㿴��
	void deleteObserver(Observer observer);
	
	//��ȻҪ�۲죬�ҷ����ı�����ҲӦ��������������֪ͨ�۲���
	void notifyObservers(String context);
}
