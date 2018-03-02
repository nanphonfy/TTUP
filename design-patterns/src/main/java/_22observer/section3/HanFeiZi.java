package _22observer.section3;

import java.util.ArrayList;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * �����ӣ���˹��ʦ�ܣ���������Ҫ����
 */
public class HanFeiZi implements Observable,IHanFeiZi{
	//������䳤���飬������еĹ۲���
	private ArrayList<Observer> observerList = new ArrayList<>();
	
	//���ӹ۲���
	@Override public void addObserver(Observer observer){
		this.observerList.add(observer);
	}
	
	//ɾ���۲���
	@Override public void deleteObserver(Observer observer){
		this.observerList.remove(observer);
	}
	
	//֪ͨ���еĹ۲���
	@Override public void notifyObservers(String context){
		for(Observer observer:observerList){
			observer.update(context);
		}
	}
	
	//������Ҫ�Է���
	@Override public void haveBreakfast(){
		System.out.println("������:��ʼ�Է���...");
		//֪ͨ���еĹ۲���
		this.notifyObservers("�������ڳԷ�");
	}
	
	//�����ӿ�ʼ������,�Ŵ���ûɶ���֣������뵽�ľ���ô��
	@Override public void haveFun(){
		System.out.println("������:��ʼ������...");
		this.notifyObservers("������������");
	}
}
