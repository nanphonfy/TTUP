package _10template_method.section4;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * H1和H2有什么差别，还真不知道，真没接触过悍马
 */
public class HummerH2Model extends HummerModel {
	@Override protected void alarm() {
		System.out.println("悍马H2鸣笛...");
	}

	@Override protected void engineBoom() {
		System.out.println("悍马H2引擎声音是这样在...");
	}
	
	@Override protected void start() {
		System.out.println("悍马H2发动...");
	}

	@Override protected void stop() {
		System.out.println("悍马H2停车...");
	}
	
	//默认没有喇叭的
	@Override protected boolean isAlarm() {
		return false;
	}
}
