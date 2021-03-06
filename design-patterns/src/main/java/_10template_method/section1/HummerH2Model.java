package _10template_method.section1;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 */
public class HummerH2Model extends HummerModel {
	//H2型号的悍马车鸣笛
	@Override public void alarm() {
		System.out.println("悍马H2鸣笛...");
	}

	//引擎轰鸣声
	@Override public void engineBoom() {
		System.out.println("悍马H2引擎声音是这样在...");
	}

	//汽车发动
	@Override public void start() {
		System.out.println("悍马H2发动...");
	}
	
	//停车
	@Override public void stop() {
		System.out.println("悍马H2停车...");
	}
	
	//开动起来
	@Override public void run(){
		//先发动汽车
		this.start();
		
		//引擎开始轰鸣
		this.engineBoom();
		
		//然后就开始跑了，跑的过程中遇到一条狗挡路，就按喇叭
		this.alarm();
		
		//到达目的地就停车
		this.stop();
	}
}
