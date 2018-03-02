package _22observer.section4;

import java.util.Vector;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public abstract class Subject {
	/**
	 * ���۲��ߵ�ְ��ǳ��򵥣�ArrayList���߳��첽������ȫ��Vector���߳�ͬ������ȫ��
	 */
	//��һ��һ���۲�������
	private Vector<Observer> obsVector = new Vector<>();
	
	//����һ���۲���
	public void addObserver(Observer o){
		this.obsVector.add(o);
	}
	
	//ɾ��һ���۲���
	public void delObserver(Observer o){
		this.obsVector.remove(o);
	}
	
	//֪ͨ���й۲���
	public void notifyObserver(){
		for(Observer o:this.obsVector){
			o.update();
		}
	}
}
