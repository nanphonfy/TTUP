package _22observer.section4;

import java.util.Vector;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public abstract class Subject {
	/**
	 * 被观察者的职责非常简单，ArrayList是线程异步，不安全；Vector是线程同步，安全。
	 */
	//定一个一个观察者数组
	private Vector<Observer> obsVector = new Vector<>();
	
	//增加一个观察者
	public void addObserver(Observer o){
		this.obsVector.add(o);
	}
	
	//删除一个观察者
	public void delObserver(Observer o){
		this.obsVector.remove(o);
	}
	
	//通知所有观察者
	public void notifyObserver(){
		for(Observer o:this.obsVector){
			o.update();
		}
	}
}
